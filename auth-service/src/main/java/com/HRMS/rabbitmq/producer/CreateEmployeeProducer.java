package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.CreateEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeProducer {
    private final RabbitTemplate template;

    public void createEmployeeAtUser(CreateEmployee employee) {
        template.convertSendAndReceive("direct-exchange-auth","key-employee-user",employee);
    }
}
