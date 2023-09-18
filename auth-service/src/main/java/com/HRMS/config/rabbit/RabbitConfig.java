package com.HRMS.config.rabbit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private final String directExchangeAuth = "direct-exchange-auth";
    private final String queueAuthCreateProfile = "queue-create-guest";
    private final String routingKeyAuth = "routing-key-auth-create-profile";

    // Rabbit config settings for create employee at user service

    private final String rootingKeyCreateEmployeeAuth = "key-employee-user";

    private final String queueAuthCreateEmployee = "queue-employee-user";

    // Mail Configuration settings
    private String routingKeyEmployeeMail="key-employee-mail";
    private String queueEmployeeMail="queue-employee-mail";
    // Admin configuration settings

    private String routingKeyCreateAdmin="key-create-admin";
    private String queueCreateAdmin="queue-create-admin";


// Beans for create guest user at user service
    @Bean
    DirectExchange directExchangeAuthMethod() {
        return new DirectExchange(directExchangeAuth);
    }

    @Bean
    Queue createQueueAuthCreateProfileMethod() {
        return new Queue(queueAuthCreateProfile);
    }

    @Bean
    public Binding bindingCreateProfileMethod(final DirectExchange directExchangeAuthMethod,
                                              final Queue createQueueAuthCreateProfileMethod) {
        return BindingBuilder
                .bind(createQueueAuthCreateProfileMethod)
                .to(directExchangeAuthMethod)
                .with(routingKeyAuth);

    }
    // Beans for create employee at employee service


    @Bean
    Queue queueEmployeeAuth() {
        return new Queue(queueAuthCreateEmployee);
    }

    @Bean
    Binding bindingEmployeeAuth(DirectExchange directExchangeAuth, Queue queueEmployeeAuth) {
        return BindingBuilder.bind(queueEmployeeAuth).to(directExchangeAuth).with(rootingKeyCreateEmployeeAuth);
    }

    // Beans for send login information mail
    @Bean

    Queue queueEmployeeMail() {
        return new Queue(queueEmployeeMail);}

    @Bean
    Binding bindingEmployeeMail(DirectExchange directExchangeAuth, Queue queueEmployeeMail) {
            return BindingBuilder.bind(queueEmployeeMail).to(directExchangeAuth).with(routingKeyEmployeeMail);
        }
    @Bean
    Queue queueCreateAdmin() {return new Queue(queueCreateAdmin);}

    @Bean
    Binding bindingCreateAdmin(DirectExchange directExchangeAuth, Queue queueCreateAdmin) {
        return BindingBuilder.bind(queueCreateAdmin).to(directExchangeAuth).with(routingKeyCreateAdmin);
    }


}
