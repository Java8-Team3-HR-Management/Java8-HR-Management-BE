package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeConsumer {

    private final AuthService service;

    @RabbitListener(queues = "queue-employee-auth")
    public Boolean createEmployee(CreateEmployee employee){
        return service.createEmployee(employee);
    }
}
