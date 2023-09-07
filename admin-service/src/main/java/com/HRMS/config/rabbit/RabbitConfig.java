package com.HRMS.config.rabbit;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitConfig {

    // Eklenen adminlerin auth service gÃ¶nderilmesi iÃ§in rabbit configlerinin tanÄ±mlanmasÄ±;
    private String exchangeCreateAdminAuth = "exchange-admin-auth";
    private String routingKeyAdminAuth = "key-admin-auth";
    private String queueAdminAuth = "queue-admin-auth";


    // TanÄ±mlanan rabbit configlerinin bean ile inject edilip bind edilmesi;

    @Bean
    DirectExchange directExchangeAuthMethod() {
        return new DirectExchange(exchangeCreateAdminAuth);
    }

    @Bean
    Queue createQueueAuthCreateProfileMethod() {
        return new Queue(queueAdminAuth);
    }

    @Bean
    public Binding bindingCreateProfileMethod(final DirectExchange directExchangeAuthMethod,
                                              final Queue createQueueAuthCreateProfileMethod) {
        return BindingBuilder
                .bind(createQueueAuthCreateProfileMethod)
                .to(directExchangeAuthMethod)
                .with(routingKeyAdminAuth);

    }

}

