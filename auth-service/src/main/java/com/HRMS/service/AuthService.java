package com.HRMS.service;

import com.HRMS.dto.request.AddUserRequestDto;
import com.HRMS.dto.request.DoLoginRequestDto;
import com.HRMS.dto.request.DoRegisterRequestDto;
import com.HRMS.dto.request.UserSaveRequestDto;
import com.HRMS.dto.response.DoLoginResponseDto;
import com.HRMS.exceptions.AuthException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IAuthMapper;
import com.HRMS.rabbitmq.model.*;
import com.HRMS.rabbitmq.producer.CreateEmployeeProducer;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.rabbitmq.producer.EmailProducer;
import com.HRMS.repository.IAuthRepository;
import com.HRMS.repository.entity.Auth;
import com.HRMS.repository.enums.ERole;
import com.HRMS.utility.JwtTokenManager;
import com.HRMS.utility.RandomPasswordGenerator;
import com.HRMS.utility.ServiceManager;
import org.springframework.stereotype.Service;
import static com.HRMS.utility.RandomPasswordGenerator.*;

import java.util.Optional;

@Service

public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final CreateProfileProducer createProfileProducer;
    private final JwtTokenManager jwtTokenManager;
    private final CreateEmployeeProducer  createEmployeeProducer;
    private final EmailProducer emailProducer;



    public AuthService(IAuthRepository repository,
                       CreateProfileProducer createProfileProducer, JwtTokenManager jwtTokenManager,CreateEmployeeProducer createEmployeeProducer,
                       EmailProducer emailProducer) {
        super(repository);
        this.repository = repository;
        this.createProfileProducer = createProfileProducer;
        this.jwtTokenManager = jwtTokenManager;
        this.createEmployeeProducer=createEmployeeProducer;
        this.emailProducer=emailProducer;

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
        if(dto.getCompanyMail()==null &&!(dto.getEmail()==null)){
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
        Optional<String> empToken= jwtTokenManager.createToken(empAuth.get().getId(),empAuth.get().getRoles());
        if (empToken.isEmpty()) throw new AuthException(ErrorType.INVALID_TOKEN);
        return DoLoginResponseDto.builder()
                .token(empToken.get())
                .role(empAuth.get().getRoles())
                .build();
    }


    public Boolean createEmployee(AddUserRequestDto dto) {
        Optional<Auth> empAuth = repository.findOptionalByEmail(dto.getEmail());
        if (!empAuth.isEmpty()) {
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }
            String mailGen = dto.getName().toLowerCase().charAt(0) + dto.getSurname().toLowerCase().trim() + "@" + dto.getCompanyName().toLowerCase().trim() + ".com";
            String pass = generateRandomPassword();
            Auth authEmployee = Auth.builder()
                    .email(dto.getEmail())
                    .password(pass)
                    .roles(ERole.EMPLOYEE)
                    .companyEmail(mailGen)
                    .build();
            save(authEmployee);
            createEmployeeProducer.createEmployeeAtUser(CreateEmployee.builder()
                    .authId(authEmployee.getId())
                    .title(dto.getTitle())
                    .salary(dto.getSalary())
                    .department(dto.getDepartment())
                    .location(dto.getLocation())
                    .membershipDate(dto.getMembershipDate())
                    .companyId(dto.getCompanyId())
                    .phone(dto.getPhone())
                    .name(dto.getName())
                    .surname(dto.getSurname())
                    .birthDate(dto.getBirthDate())
                    .companyName(dto.getCompanyName())
                    .birthPlace(dto.getBirthPlace())
                    .email(dto.getEmail())
                    .build());
            emailProducer.sendMailActivationMessage(SendActivationEmail.builder()
                    .email(dto.getEmail())
                    .companyMail(mailGen)
                    .password(pass)
                    .build());

        return true;
    }
    public Boolean addAdmin(AddUserRequestDto dto){
        Optional<Auth> empAuth = repository.findOptionalByEmail(dto.getEmail());
        if (!empAuth.isEmpty()) {
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }else {
            Auth authAdmin=Auth.builder()
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .roles(ERole.ADMIN)
                    .build();
            save(authAdmin);
            createProfileProducer.sendCreateAdminMessage(CreateAdmin.builder()
                    .name(dto.getName())
                     .authId(authAdmin.getId())
                    .surname(dto.getSurname())
                    .email(dto.getEmail())
                    .build());
        }
        return true;
    }
    public Boolean addManager(AddUserRequestDto dto){
        Optional<Auth> managerOpt= repository.findOptionalByEmail(dto.getEmail());
        if(!managerOpt.isEmpty()){
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }
        Auth manager=Auth.builder()
                .email(dto.getEmail())
                .companyEmail(dto.getCompanyEmail())
                .password(dto.getPassword())
                .roles(ERole.MANAGER)
                .build();
        save(manager);
        createProfileProducer.sendCreateManagerMessage(CreateManager.builder()
                .name(dto.getName())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .companyEmail(dto.getCompanyEmail())
                .companyName(dto.getCompanyName())
                .title(dto.getTitle())
                .salary(dto.getSalary())
                .build());
        return true;
    }

}