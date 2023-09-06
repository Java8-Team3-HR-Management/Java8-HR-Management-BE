package com.hrms.service;

import com.hrms.dto.request.DoLoginRequestDto;
import com.hrms.dto.request.DoRegisterRequestDto;
import com.hrms.exceptions.AuthException;
import com.hrms.exceptions.ErrorType;
import com.hrms.manager.IUserManager;
import com.hrms.mapper.IAuthMapper;
import com.hrms.rabbitmq.model.CreateProfile;
import com.hrms.rabbitmq.producer.CreateProfileProducer;
import com.hrms.repository.IAuthRepository;
import com.hrms.repository.entity.Auth;
import com.hrms.utility.JwtTokenManager;
import com.hrms.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final IUserManager userManager;
    private final CreateProfileProducer createProfileProducer;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository repository,IUserManager userManager,
                       CreateProfileProducer createProfileProducer, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;
        this.userManager = userManager;
        this.createProfileProducer = createProfileProducer;
        this.jwtTokenManager = jwtTokenManager;
    }
    public Boolean register(DoRegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
        repository.findOptionalByUsername(dto.getUsername())
                .ifPresent(auth -> {
                    throw new AuthException(ErrorType.REGISTER_KULLANICIADI_KAYITLI);
                });

        Auth auth = IAuthMapper.INSTANCE.authFromDto(dto);
        repository.save(auth);

        createProfileProducer.sendCreateProfileMessage(
                CreateProfile.builder()
                        .authid(auth.getId())
                        .username(dto.getUsername())
                        .email(dto.getEmail())
                        .build()
        );
        return true;
    }

    public String login(DoLoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());

        if(auth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty()) throw new AuthException(ErrorType.BAD_REQUEST_ERROR);
        return token.get();
    }

    public Optional<Auth> loginAlternatif(DoLoginRequestDto dto){
        return repository.findOptionalByUsernameAndPassword(dto.getUsername(),dto.getPassword());
    }

}