package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.CreateAdmin;
import com.HRMS.rabbitmq.model.CreateEmployee;
import com.HRMS.rabbitmq.model.CreateManager;
import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserConsumer {
    private final UserService service;

    @RabbitListener(queues = "queue-create-guest")
    public void sendActivationMail(CreateProfile profile){
        service.createGuest(profile);
    }
    @RabbitListener(queues = "queue-employee-user")
    public void createEmployee(CreateEmployee employee){service.createEmployee(employee);}

    @RabbitListener(queues = "queue-create-admin")
    public void createAdmin(CreateAdmin admin){service.createAdmin(admin);}
    @RabbitListener(queues = "queue-create-manager")
    public void createManager(CreateManager manager){service.createManager(manager);}
}
