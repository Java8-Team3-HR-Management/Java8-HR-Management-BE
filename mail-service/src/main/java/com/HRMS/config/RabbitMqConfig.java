package com.HRMS.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
    private String mailRegisterQueue = "mail-register-queue";
    //rabbit config send activation mail for employee
    private final String queueSendActivationMail = "queue-employee-mail";
    @Bean
    Queue mailRegisterQueue(){
        return new Queue(mailRegisterQueue);
    }
    // rabbit bean send activation mail for employee
    @Bean
    Queue queueSendActivationMail(){
        return new Queue(queueSendActivationMail);
    }
}
