package com.HRMS.service;

import com.HRMS.dto.request.AddManagerRequestDto;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.exceptions.ManagerException;
import com.HRMS.mapper.IManagerMapper;
import com.HRMS.repository.IManagerRepository;
import com.HRMS.repository.entity.Manager;
import com.HRMS.repository.enums.EStatus;
import com.HRMS.utils.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ManagerService extends ServiceManager<Manager, String> {
private final IManagerRepository repository;
private final IManagerMapper mapper;

    public ManagerService(IManagerRepository repository,IManagerMapper mapper) {
        super(repository);
        this.repository = repository;
        this.mapper=mapper;
    }


    public Boolean addManager(AddManagerRequestDto dto){
        Optional<Manager> managerOpt= repository.findById(dto.getEmail());
        if(managerOpt.isPresent()){
            throw new ManagerException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }
        Manager manager=mapper.toManagerFromDto(dto);
        manager.setStatus(EStatus.PENDING);
        save(manager);
        return true;
    }
}
