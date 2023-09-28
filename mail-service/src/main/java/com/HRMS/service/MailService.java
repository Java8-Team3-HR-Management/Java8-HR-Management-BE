package com.HRMS.service;

import com.HRMS.dto.request.SendActivationMailRequestDto;
import com.HRMS.mapper.IMailMapper;
import com.HRMS.rabbitmq.model.ForgotPassword;
import com.HRMS.rabbitmq.model.SendActivationEmail;
import com.HRMS.repository.IMailRepository;
import com.HRMS.repository.entity.Mail;
import com.HRMS.utils.SendMailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class MailService {


    private final SendMailService mailService;
    private final IMailRepository repository;

    public void sendRegisterEmployeeInfo(SendActivationEmail email){
        SendActivationMailRequestDto dto = SendActivationMailRequestDto.builder()
                .email(email.getEmail())
                .companyMail(email.getCompanyMail())
                .password(email.getPassword())
                .build();
        System.out.println(dto);
        Mail mail= IMailMapper.INSTANCE.toMailFromDto(dto);
        repository.save(mail);
        mailService.sendMail(email.getEmail(), "Login Informations is below. \n",
                "Login mail address: "+email.getCompanyMail()+" \nLogin password:"+email.getPassword()
                );

/**
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(mailRegisterModel.getEmail());
        mailMessage.setSubject("Giriş Bilgileri");
        mailMessage.setText(
                "Giriş bilgileriniz aşağıdaki gibidir.\n"
                        + "Şirket Maili " + mailRegisterModel.getCompanyMail()
                        + "\nŞifre: " + mailRegisterModel.getPassword()
 **/
    }
    public void sendForgotPasswordInfo(ForgotPassword mail){
    mailService.sendMail(mail.getEmail(), "Your password is below. \n", "Your password: "+mail.getPassword());
    }

}
