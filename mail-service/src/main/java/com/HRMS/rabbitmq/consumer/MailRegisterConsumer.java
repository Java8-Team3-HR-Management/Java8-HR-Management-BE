package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.MailRegisterModel;
import com.HRMS.service.MailSenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailRegisterConsumer {
    private final MailSenderService mailSenderService;

    @RabbitListener(queues = "mail-register-queue")
    public void sendRegisterUsersInfo(MailRegisterModel mailRegisterModel){
        mailSenderService.sendRegisterEmployeeInfo(mailRegisterModel);
    }

}
