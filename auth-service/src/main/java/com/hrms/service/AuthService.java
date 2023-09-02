package com.hrms.service;

import com.hrms.dto.request.DoRegisterRequestDto;
import com.hrms.exceptions.AuthException;
import com.hrms.exceptions.ErrorType;
import com.hrms.rabbitmq.producer.CreateProfileProducer;
import com.hrms.repository.IAuthRepository;
import com.hrms.repository.entity.Auth;
import com.hrms.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final CreateProfileProducer createProfileProducer;

    public AuthService(IAuthRepository repository,
                       CreateProfileProducer createProfileProducer) {
        super(repository);
        this.repository = repository;
        this.createProfileProducer = createProfileProducer;

    }

    public Boolean register(DoRegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
        repository.findOptionalByUsername(dto.getUsername())
                .ifPresent(auth -> {
                    throw new AuthException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
                });
        return true;
    }

    }



