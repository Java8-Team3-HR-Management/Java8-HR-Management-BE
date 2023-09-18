package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.SendActivationEmail;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailProducer {
    private final RabbitTemplate  rabbitTemplate;
    public void sendMailActivationMessage(SendActivationEmail email){
        rabbitTemplate.convertAndSend("direct-exchange-auth",
                "key-employee-mail",email);
    }
}
