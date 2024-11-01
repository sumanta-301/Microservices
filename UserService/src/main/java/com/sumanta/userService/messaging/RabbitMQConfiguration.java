package com.sumanta.userService.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class RabbitMQConfiguration {
    @Bean

    public Queue userItemQueue() {
        return new Queue("userItemQueue");
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
    return new Jackson2JsonMessageConverter();
    }
    @Bean
//    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
//        System.out.println("inside the rabbit template in configuration ");
//        RabbitTemplate rabbitTemplate =
//                new RabbitTemplate(connectionFactory);
//        rabbitTemplate.setMessageConverter(jsonMessageConverter());
//        System.out.println(rabbitTemplate);
//        return rabbitTemplate;
//    }
    public AmqpTemplate amqpTemplate (ConnectionFactory connectionFactory) {
        final RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(jsonMessageConverter());
        return template;
    }
}
