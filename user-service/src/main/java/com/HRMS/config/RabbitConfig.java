package com.HRMS.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {
    private final String queueCreateProfile= "queue-create-guest";

    @Bean
    Queue queueCreateProfile(){return new Queue(queueCreateProfile);}


}
