package com.HRMS.service;

import com.HRMS.rabbitmq.model.MailRegisterModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailSenderService {

    private final JavaMailSender javaMailSender;

    public void sendRegisterEmployeeInfo(MailRegisterModel mailRegisterModel){

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom("${spring.mail.username}");
        mailMessage.setTo(mailRegisterModel.getEmail());
        mailMessage.setSubject("Giriş Bilgileri");
        mailMessage.setText(
                "Giriş bilgileriniz aşağıdaki gibidir.\n"
                        + "Şirket Maili " + mailRegisterModel.getCompanyMail()
                        + "\nŞifre: " + mailRegisterModel.getPassword()
        );
        javaMailSender.send(mailMessage);
    }

}
