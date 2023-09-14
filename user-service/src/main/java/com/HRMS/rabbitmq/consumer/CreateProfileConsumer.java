package com.HRMS.rabbitmq.consumer;

import com.HRMS.rabbitmq.model.CreateProfile;
import com.HRMS.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileConsumer {
    private final UserService service;

    @RabbitListener(queues = "queue-create-guest")
    public void sendActivationMail(CreateProfile profile){
        service.createGuest(profile);
    }
}
