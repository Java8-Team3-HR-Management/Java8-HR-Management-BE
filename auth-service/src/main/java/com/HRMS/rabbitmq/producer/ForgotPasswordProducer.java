package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.ForgotPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ForgotPasswordProducer {
    private final RabbitTemplate rabbitTemplate;

    public static void sendMailForgotPassword(ForgotPassword email){
        rabbitTemplate.convertAndSend("direct-exchange-auth","key-forgot-password",email);
    }
}
