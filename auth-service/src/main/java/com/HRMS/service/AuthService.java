package com.HRMS.service;

import com.HRMS.dto.request.*;
import com.HRMS.dto.response.DoLoginResponseDto;
import com.HRMS.exceptions.AuthException;
import com.HRMS.exceptions.ErrorType;
import com.HRMS.mapper.IAuthMapper;
import com.HRMS.rabbitmq.model.*;
import com.HRMS.rabbitmq.producer.CreateEmployeeProducer;
import com.HRMS.rabbitmq.producer.CreateProfileProducer;
import com.HRMS.rabbitmq.producer.EmailProducer;
import com.HRMS.rabbitmq.producer.ForgotPasswordProducer;
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

public class AuthService extends ServiceManager<Auth, Long> {
    private final IAuthRepository repository;
    private final CreateProfileProducer createProfileProducer;
    private final JwtTokenManager jwtTokenManager;
    private final CreateEmployeeProducer createEmployeeProducer;
    private final EmailProducer emailProducer;
    private final  ForgotPasswordProducer forgotPasswordProducer;


    public AuthService(IAuthRepository repository,
                       CreateProfileProducer createProfileProducer, JwtTokenManager jwtTokenManager, CreateEmployeeProducer createEmployeeProducer,
                       EmailProducer emailProducer,ForgotPasswordProducer forgotPasswordProducer) {
        super(repository);
        this.repository = repository;
        this.createProfileProducer = createProfileProducer;
        this.jwtTokenManager = jwtTokenManager;
        this.createEmployeeProducer = createEmployeeProducer;
        this.emailProducer = emailProducer;
        this.forgotPasswordProducer =forgotPasswordProducer;

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
                        .authId(auth.getId())
                        .email(dto.getEmail())
                        .name(dto.getName())
                        .surname(dto.getSurname())
                        .password(dto.getPassword())
                        .build()
        );
        return true;
    }

    public DoLoginResponseDto login(DoLoginRequestDto dto) {
        if (dto.getCompanyMail() == null && !(dto.getEmail() == null)) {
            Optional<Auth> auth = repository.findOptionalByEmailAndPassword(dto.getEmail(), dto.getPassword());
            if (auth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
            Optional<String> userToken = jwtTokenManager.createToken(auth.get().getId(), auth.get().getRoles());
            if (userToken.isEmpty()) throw new AuthException(ErrorType.INVALID_TOKEN);
            return DoLoginResponseDto.builder()
                    .token(userToken.get())
                    .role(auth.get().getRoles())
                    .build();
        }
        Optional<Auth> empAuth = repository.findOptionalByCompanyEmailAndPassword(dto.getCompanyMail(), dto.getPassword());
        if (empAuth.isEmpty()) throw new AuthException(ErrorType.DOLOGIN_INVALID_USERNAME_PASSWORD);
        Optional<String> empToken = jwtTokenManager.createToken(empAuth.get().getId(), empAuth.get().getRoles());
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

    public Boolean addAdmin(AddUserRequestDto dto) {
        Optional<Auth> empAuth = repository.findOptionalByEmail(dto.getEmail());
        if (!empAuth.isEmpty()) {
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        } else {
            Auth authAdmin = Auth.builder()
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

    public Boolean addManager(AddUserRequestDto dto) {
        Optional<Auth> managerOpt = repository.findOptionalByEmail(dto.getEmail());
        if (!managerOpt.isEmpty()) {
            throw new AuthException(ErrorType.MAIL_ALREADY_HAS_BEEN);
        }
        Auth manager = Auth.builder()
                .email(dto.getEmail())
                .companyEmail(dto.getCompanyEmail())
                .password(dto.getPassword())
                .roles(ERole.MANAGER)
                .build();
        save(manager);
        createProfileProducer.sendCreateManagerMessage(CreateManager.builder()
                .name(dto.getName())
                .authId(manager.getId())
                .surname(dto.getSurname())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .companyId(dto.getCompanyId())
                .companyEmail(dto.getCompanyEmail())
                .companyName(dto.getCompanyName())
                .title(dto.getTitle())
                .salary(dto.getSalary())
                .build());
        return true;
    }

    public Boolean forgotPassword(String email){
     Optional<Auth> auth = repository.findOptionalByEmail(email);
     if(auth.isEmpty()) throw new AuthException(ErrorType.FORGOT_PASSWORD_INVALID_EMAIL);
     if (auth.get().isState()==false)throw new AuthException(ErrorType.BAD_REQUEST_ERROR);

     forgotPasswordProducer.sendMailForgotPassword(ForgotPassword.builder()
             .email(email)
             .authId(auth.get().getId())
             .password(auth.get().getPassword())
             .build());
        return true;
    }

}