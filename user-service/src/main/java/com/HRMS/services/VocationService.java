package com.HRMS.services;

import com.HRMS.dto.requests.CreateVocationRequestDto;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.UserException;
import com.HRMS.mapper.IUserMapper;
import com.HRMS.repository.IUserRepository;
import com.HRMS.repository.IVocationRepository;
import com.HRMS.repository.entity.Vocation;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class VocationService extends ServiceManager<Vocation,String> {
    private final IVocationRepository vocationRepository;
    private final UserService service;
    private final IUserRepository repository;


    public VocationService(IVocationRepository vocationRepository, UserService service, IUserRepository repository) {
        super(vocationRepository);
        this.service = service;
        this.repository = repository;
        this.vocationRepository = vocationRepository;
    }

    public Boolean createVocation(CreateVocationRequestDto dto) {
        if (repository.findById(dto.getUserId()).isEmpty())
            throw new UserException(ErrorType.ID_NOT_FOUND);
        if (dto.getStartOfVocationDate() == null && dto.getEndOfVocationDate() == null)
            throw new UserException(ErrorType.VOCATION_NOT_CREATED);
        Vocation vocation = IUserMapper.INSTANCE.toVocation(dto);
        long daysBetween = ChronoUnit.DAYS.between(dto.getStartOfVocationDate(), dto.getEndOfVocationDate());
        if (daysBetween < 0) throw new UserException(ErrorType.VOCATION_DURATION_NOT_BE_MINUS);
        vocation.setVocationDuration(daysBetween);
        vocation.setStartOfVocationDate(vocation.getStartOfVocationDate().plusDays(1));
        vocation.setEndOfVocationDate(vocation.getEndOfVocationDate().plusDays(1));
        save(vocation);
        return true;
    }


    public List<Vocation> findAllVocationPending() {
        List<Vocation> findAllList= vocationRepository.findAll();
        List<Vocation> findAllVocationPendingList= new ArrayList<>();
        System.out.println("Bekleme Listesi-->");
        findAllList.forEach(x->{
            if(x.getVocationStatus().equals(EStatus.PENDING))
                findAllVocationPendingList.add(x);
        });
        return findAllVocationPendingList;

    }

    public Boolean approveVocationRequest(String id) {
        Optional<Vocation> vocation = findById(id);
        if(vocation.isEmpty()){
            throw new UserException(ErrorType.VOCATION_NOT_VALID);
        }
        vocation.get().setVocationStatus(EStatus.ACCEPT);
        vocation.get().setResponseOfVocationRequestDate(LocalDate.now());
        update(vocation.get());
        return true;
    }
    public Boolean rejectVocationRequest(String id) {
        Optional<Vocation> vocation = findById(id);
        if(vocation.isEmpty()){
            throw new UserException(ErrorType.VOCATION_NOT_VALID);
        }
        vocation.get().setVocationStatus(EStatus.REJECT);
        vocation.get().setResponseOfVocationRequestDate(LocalDate.now());
        update(vocation.get());
        return true;
    }
    public List<Vocation> sortingList(){
        List<Vocation> vocationList = findAll();
        List<Vocation> sortedList = sortByFifo(vocationList);
        return sortedList;
    }

    public List<Vocation> sortByFifo(List<Vocation> vocationList) {
        vocationList.sort(Comparator.comparing(Vocation::getCreateDate));
        List<Vocation> sortedList = new ArrayList<>();
        for (Vocation vocation : vocationList) {
            if (vocation.getVocationStatus() == EStatus.PENDING) {
                sortedList.add(vocation);
            }
        }
        for (Vocation vocation : vocationList) {
            if (vocation.getVocationStatus() == EStatus.ACCEPT) {
                sortedList.add(vocation);
            }
        }
        for (Vocation vocation : vocationList) {
            if (vocation.getVocationStatus() == EStatus.REJECT) {
                sortedList.add(vocation);
            }
        }
        return sortedList;


    }

}
