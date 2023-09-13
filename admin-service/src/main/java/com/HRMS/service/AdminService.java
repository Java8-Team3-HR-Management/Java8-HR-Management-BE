package com.HRMS.service;

import com.HRMS.dto.request.AddAdminRequestDto;
import com.HRMS.exceptions.AdminException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IAdminMapper;
import com.HRMS.rabbitmq.model.CreateAdminModel;

import com.HRMS.rabbitmq.producer.AdminProducer;
import com.HRMS.repository.IAdminRepository;
import com.HRMS.repository.entity.Admin;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AdminService  {
    private final IAdminRepository repository;
    private final AdminProducer createAdminProducer;


    public AdminService(IAdminRepository repository, AdminProducer createAdminProducer){
        super();
        this.repository=repository;
        this.createAdminProducer=createAdminProducer;

    }


    public Boolean addAdmin(AddAdminRequestDto dto){
        Boolean check=createAdminProducer.createAdminAtAuth(CreateAdminModel.builder()
                .email(dto.getEmail())
                .build());
        if (check){
            Optional<Admin> empOpt= repository.findOptionalByEmail(dto.getEmail());
            if (empOpt.isPresent()) throw new AdminException(ErrorType.ADMIN_ALREADY_EXIST);
        }
        Admin emp= IAdminMapper.INSTANCE.toAdminFromDto(dto);
        repository.save(emp);
        return true;

    }




}