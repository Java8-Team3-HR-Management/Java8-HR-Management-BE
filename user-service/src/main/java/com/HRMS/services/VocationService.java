package com.HRMS.services;

import com.HRMS.dto.requests.CreateVocationRequestDto;
import com.HRMS.dto.requests.UpdateVacationRequestDto;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.UserException;
import com.HRMS.mapper.IUserMapper;
import com.HRMS.repository.IUserRepository;
import com.HRMS.repository.IVocationRepository;
import com.HRMS.repository.entity.Vocation;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.JwtTokenManager;
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
    private final JwtTokenManager tokenManager;


    public VocationService(IVocationRepository vocationRepository, UserService service, IUserRepository repository,
                           JwtTokenManager tokenManager) {
        super(vocationRepository);
        this.service = service;
        this.repository = repository;
        this.vocationRepository = vocationRepository;
        this.tokenManager=tokenManager;
    }

    public Boolean createVocation(CreateVocationRequestDto dto,String token) {
        Optional<Long> id=tokenManager.getByIdFromToken(token);
        if (id.isEmpty()) {
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
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

    public Boolean updateVocationRequest(UpdateVacationRequestDto dto, String token) {
        Optional<Long> idToken=tokenManager.getByIdFromToken(token);
        if (idToken.isEmpty()) {
            throw new UserException(ErrorType.USER_NOT_FOUND);
        }
        Optional<String> role = tokenManager.getRoleFromToken(token.toString());
        if (role.isEmpty()) {
            throw new UserException(ErrorType.UNAUTHORIZED_USER);
        }
        Optional<Vocation> vocation = findById(dto.getUserId());
        if(vocation.isEmpty()){
            throw new UserException(ErrorType.VOCATION_NOT_VALID);
        }
        vocation.get().setVocationStatus(dto.getVocationStatus());
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
