package com.HRMS.config;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitConfig {
    private final String queueCreateProfile= "queue-create-guest";
    private final String queueAuthCreateEmployee = "queue-employee-user";
    private final String queueCreateAdmin = "queue-create-admin";

    @Bean
    Queue queueCreateProfile(){return new Queue(queueCreateProfile);}
    @Bean
    Queue queueCreateEmployee(){return new Queue(queueAuthCreateEmployee);}
    @Bean
    Queue queueCreateAdmin(){return new Queue(queueCreateAdmin);}


}
