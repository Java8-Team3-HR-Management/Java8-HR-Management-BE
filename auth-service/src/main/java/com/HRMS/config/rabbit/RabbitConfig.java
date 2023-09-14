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

    // Rabbit config settings for create employee at employee service

    private final String exchangeCreateEmployeeAuth = "exchange-create-auth";

    private final String rootingKeyCreateEmployeeAuth = "key-employee-auth";

    private final String queueAuthCreateEmployee = "queue-employee-auth";

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
    DirectExchange directExchangeEmployeeAuth() {
        return new DirectExchange(exchangeCreateEmployeeAuth);
    }

    @Bean
    Queue queueEmployeeAuth() {
        return new Queue(queueAuthCreateEmployee);
    }

    @Bean
    Binding bindingEmployeeAuth(DirectExchange directExchangeEmployeeAuth, Queue queueEmployeeAuth) {
        return BindingBuilder.bind(queueEmployeeAuth).to(directExchangeEmployeeAuth).with(rootingKeyCreateEmployeeAuth);
    }

    // Beans for send login information mail


}
