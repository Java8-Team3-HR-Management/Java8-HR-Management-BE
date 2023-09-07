package com.HRMS.config.rabbit;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    // Eklenen çalışanların auth service gönderilmesi için rabbit configlerinin tanımlanması
    private String exchangeCreateEmployeeAuth = "exchange-employee-auth";
    private String routingKeyEmployeeAuth = "key-employee-auth";
    private String queueEmployeeAuth = "queue-employee-auth";

    //
    private String routingKeyEmployeeMail="key-employee-mail";
    private String queueEmployeeMail="queue-employee-mail";
    // Tanımlanan rabbit configlerinin bean ile inject edilip bind edilmesi
    @Bean
    DirectExchange directExchangeAuthMethod() {return new DirectExchange(exchangeCreateEmployeeAuth);}
    @Bean
    Queue createQueueAuthCreateEmployeeMethod() {return new Queue(queueEmployeeAuth);}
    @Bean
    public Binding bindingCreateEmployeeAuthMethod(final DirectExchange directExchangeAuthMethod,final Queue createQueueAuthCreateEmployeeMethod) {
        return BindingBuilder.bind(createQueueAuthCreateEmployeeMethod).to(directExchangeAuthMethod).with(routingKeyEmployeeAuth);
    }
    @Bean
    Queue createQueueEmployeeMailMethod() {return new Queue(queueEmployeeMail);}
    @Bean
    public Binding bindingEmployeeMailMethod(final DirectExchange directExchangeAuthMethod,final Queue createQueueEmployeeMailMethod) {
        return BindingBuilder.bind(createQueueEmployeeMailMethod).to(directExchangeAuthMethod).with(routingKeyEmployeeMail);
    }


}
