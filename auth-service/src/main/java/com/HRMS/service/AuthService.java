package com.HRMS.service;

import com.HRMS.dto.request.DoLoginRequestDto;
import com.HRMS.dto.request.DoRegisterRequestDto;
import com.HRMS.exceptions.AuthException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.manager.IUserManager;
import com.HRMS.mapper.IAuthMapper;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.repository.IAuthRepository;
import com.HRMS.repository.entity.Auth;
import com.HRMS.repository.enums.ERole;
import com.HRMS.utility.JwtTokenManager;
import com.HRMS.utility.RandomPasswordGenerator;
import com.HRMS.utility.ServiceManager;
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
        this.jwtTokenManager=jwtTokenManager;
    }
    public Boolean register(DoRegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
        repository.findOptionalByEmail(dto.getEmail())
                .ifPresent(auth -> {
                    throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
                });

        Auth auth = IAuthMapper.INSTANCE.authFromDto(dto);
        repository.save(auth);

        createProfileProducer.sendCreateProfileMessage(
                CreateProfile.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .build()
        );
        return true;
    }
    public String login(DoLoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByEmail(dto.getEmail());

        if(auth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty()) throw new AuthException(ErrorType.BAD_REQUEST_ERROR);
        return token.get();
    }



    public Boolean createEmployee(CreateEmployee employee) {
        // Rabbitten gelen mesajın dataya kaydedilmesi
        Optional<Auth> empAuth=repository.findOptionalByEmail(employee.getEmail());
        if(empAuth.isPresent()) {
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }
        if(empAuth.isEmpty()) {
            Auth authEmployee = Auth.builder()
                    .email(employee.getEmail())
                    .password(RandomPasswordGenerator.generateRandomPassword())
                    .roles(ERole.EMPLOYEE)
                    .build();
            repository.save(authEmployee);
// TODO: 5.09.2023 Gelen employee datasının rabbitten mail service e gönderilmesi yazılacak.
        }

        return true;
    }
}