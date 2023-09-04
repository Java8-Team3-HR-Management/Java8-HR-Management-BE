package com.hrms.config.rabbit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    private final String directExchangeAuth = "direct-exchange-auth";
    private final String queueAuthCreateProfile = "queue-auth-create-profile";
    private final String routingKetAuth = "routing-key-auth-create-profile";

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
                .with(routingKetAuth);

    }
}
