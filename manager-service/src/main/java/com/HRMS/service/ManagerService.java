package com.HRMS.service;

import com.HRMS.repository.entity.Manager;
import com.HRMS.utils.ServiceManager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;

@Service
public class ManagerService extends ServiceManager<Manager, String> {


    public ManagerService(MongoRepository<Manager, String> repository) {
        super(repository);
    }
}
