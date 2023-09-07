package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.MailRegisterModel;
import com.HRMS.rabbitmq.model.SendActivationEmail;
import com.HRMS.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SendActivationMailConsumer {
 private final MailService mailService;
@RabbitListener(queues = "queue-employee-mail")
public void sendActivationMail(SendActivationEmail email){
    mailService.sendRegisterEmployeeInfo(email);
}
}
