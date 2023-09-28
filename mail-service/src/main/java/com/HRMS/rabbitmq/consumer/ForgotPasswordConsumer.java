package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.ForgotPassword;
import com.HRMS.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordConsumer {
    private final MailService mailService;

    @RabbitListener(queues = "queue-forgot-password")
    public void sendActivationMail(ForgotPassword email){
        mailService.sendForgotPasswordInfo(email);
    }
}
