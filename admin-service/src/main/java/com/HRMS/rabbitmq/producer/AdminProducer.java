package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.CreateAdminModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminProducer {

    private final RabbitTemplate rabbitTemplate;

    public Boolean createAdminAtAuth(CreateAdminModel admin){
        rabbitTemplate.convertSendAndReceive("exchange-employee-auth","key-employee-auth",admin);
        return true;
    }
}