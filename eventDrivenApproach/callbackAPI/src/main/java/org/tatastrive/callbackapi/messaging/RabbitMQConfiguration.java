package org.tatastrive.callbackapi.messaging;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
    public Queue deadLetterQueue() {
       return QueueBuilder.durable("deadLetterQueue").build();
    }
    @Bean
    public Queue parkingQueue() {
        return QueueBuilder.durable("parkingQueue")
                .deadLetterExchange("")
                .deadLetterRoutingKey("deadLetterQueue")
                .build();
    }

    @Bean
    public Queue partnerCallbackQueue() {
        return QueueBuilder.durable("partnerCallbackQueue")
                .deadLetterExchange("")
                .deadLetterRoutingKey("deadLetterQueue")
                .build();
    }
    @Bean
    public Queue digihubBasicBeneficiaryQueue() {
        return new Queue("digihubBasicBeneficiaryQueue");
    }
    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate =
                new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
