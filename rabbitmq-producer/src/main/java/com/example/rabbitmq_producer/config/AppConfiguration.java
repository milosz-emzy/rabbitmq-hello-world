package com.example.rabbitmq_producer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public Queue hello() {
        return new Queue("queue-hello");
    }

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("emzy.direct");
    }

    @Bean
    public Binding directBinding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with("orange-key");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("emzy.topic");
    }

    /*
        Topic exchange
        * (star) can substitute for exactly one word.
        # (hash) can substitute for zero or more words.
    */

    @Bean
    public Binding topicBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("*.rabbit-key");
    }

    @Bean
    public Binding topicLazyBinding(Queue queue, TopicExchange topicExchange) {
        return BindingBuilder
                .bind(queue)
                .to(topicExchange)
                .with("lazy.#");
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());
        return template;
    }
}
