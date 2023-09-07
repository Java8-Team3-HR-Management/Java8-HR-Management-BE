package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.CreateEmployee;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeProducer {
    private final RabbitTemplate template;

    public void createEmployeeAtAuth(CreateEmployee employee) {
        template.convertSendAndReceive("exchange-employee-auth","key-employee-auth",employee);

    }
}
