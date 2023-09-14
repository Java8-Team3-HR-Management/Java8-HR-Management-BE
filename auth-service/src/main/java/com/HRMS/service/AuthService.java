package com.HRMS.service;

import com.HRMS.dto.request.DoLoginRequestDto;
import com.HRMS.dto.request.DoRegisterRequestDto;
import com.HRMS.dto.response.DoLoginResponseDto;
import com.HRMS.exceptions.AuthException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IAuthMapper;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.repository.IAuthRepository;
import com.HRMS.repository.entity.Auth;
import com.HRMS.repository.enums.ERole;
import com.HRMS.utility.JwtTokenManager;
import com.HRMS.utility.ServiceManager;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final CreateProfileProducer createProfileProducer;
    private final JwtTokenManager jwtTokenManager;



    public AuthService(IAuthRepository repository,
                       CreateProfileProducer createProfileProducer, JwtTokenManager jwtTokenManager) {
        super(repository);
        this.repository = repository;

        this.createProfileProducer = createProfileProducer;
        this.jwtTokenManager = jwtTokenManager;

    }

    public Boolean register(DoRegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getPasswordConfirm()))
            throw new AuthException(ErrorType.REGISTER_PASSWORDS_NOT_MATCH);
        repository.findOptionalByEmail(dto.getEmail())
                .ifPresent(auth -> {
                    throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
                });

        Auth auth = IAuthMapper.INSTANCE.authFromDto(dto);
        auth.setRoles(ERole.GUEST);
        save(auth);

        createProfileProducer.sendCreateProfileMessage(
                CreateProfile.builder()
                        .authid(auth.getId())
                        .email(dto.getEmail())
                        .nameSurname(dto.getNameSurname())
                        .password(dto.getPassword())
                        .build()
        );
        return true;
    }

    public DoLoginResponseDto login(DoLoginRequestDto dto) {
        if(dto.getCompanyMail()==null ||!(dto.getEmail()==null)){
            Optional<Auth> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
            if (auth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
    Optional<String> userToken= jwtTokenManager.createToken(auth.get().getId(),auth.get().getRoles());
    if (userToken.isEmpty()) throw new AuthException(ErrorType.INVALID_TOKEN);
            return DoLoginResponseDto.builder()
                    .token(userToken.get())
                    .role(auth.get().getRoles())
                    .build();}
         Optional<Auth> empAuth = repository.findOptionalByCompanyEmailAndPassword(dto.getCompanyMail(), dto.getPassword());
        if (empAuth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
        Long tokenId=Long.parseLong(empAuth.get().getEmployeeId());
        Optional<String> empToken= jwtTokenManager.createToken(tokenId,empAuth.get().getRoles());
        if (empToken.isEmpty()) throw new AuthException(ErrorType.INVALID_TOKEN);
        return DoLoginResponseDto.builder()
                .token(empToken.get())
                .role(empAuth.get().getRoles())
                .build();
    }


    public Boolean createEmployee(CreateEmployee employee) {
        // Rabbitten gelen mesajın dataya kaydedilmesi
        Optional<Auth> empAuth = repository.findOptionalByEmail(employee.getEmail());
      //  if (empAuth.isPresent()) {
        //    throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        //}

        if (empAuth.isEmpty()) {
        Auth authEmployee = Auth.builder()
                    .email(employee.getEmail())
                    .password(employee.getPassword())
                    .roles(ERole.EMPLOYEE)
                    .companyEmail(employee.getCompanyEmail())
                    .employeeId(employee.getEmployeeId())
                    .build();
            save(authEmployee);
        }
        return true;


    }

}