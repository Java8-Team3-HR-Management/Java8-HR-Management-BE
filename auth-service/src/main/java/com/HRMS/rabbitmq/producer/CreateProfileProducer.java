package com.HRMS.rabbitmq.producer;

import com.HRMS.rabbitmq.model.CreateAdmin;
import com.HRMS.rabbitmq.model.CreateProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateProfileProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendCreateProfileMessage(CreateProfile createProfile){
        rabbitTemplate.convertAndSend("direct-exchange-auth",
                "routing-key-auth-create-profile",
                createProfile);
    }
    public void sendCreateAdminMessage(CreateAdmin admin){

        rabbitTemplate.convertAndSend("direct-exchange-auth",
                "key-create-admin",
                admin);
    }
}