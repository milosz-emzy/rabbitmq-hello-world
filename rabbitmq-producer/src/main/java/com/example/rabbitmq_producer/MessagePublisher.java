package com.example.rabbitmq_producer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
public class MessagePublisher {

    private static final Logger log = LogManager.getLogger(MessagePublisher.class);
    private final RabbitTemplate template;
    private final DirectExchange directExchange;
    private final TopicExchange topicExchange;

    public MessagePublisher(RabbitTemplate template, DirectExchange directExchange, TopicExchange topicExchange) {
        this.template = template;
        this.directExchange = directExchange;
        this.topicExchange = topicExchange;
    }

    public void publish() {
        publishOrange();
        publishRabbit();
        publishAnotherRabbit();
        publishLazyRabbit();
        publishLazy();

        log.info("PUBLISHED!!!");
    }

    private void publishOrange() {
        var message = new CustomMessage(
                UUID.randomUUID(),
                "Message for orange-key",
                Date.from(Instant.now())
        );
        template.convertAndSend(directExchange.getName(), "orange-key", message);
    }

    private void publishRabbit() {
        var message = new CustomMessage(
                UUID.randomUUID(),
                "Message for rabbit-key",
                Date.from(Instant.now())
        );
        template.convertAndSend(directExchange.getName(), "rabbit-key", message);
    }

    private void publishAnotherRabbit() {
        var message = new CustomMessage(
                UUID.randomUUID(),
                "Message for another.rabbit-key",
                Date.from(Instant.now())
        );
        template.convertAndSend(topicExchange.getName(), "another.rabbit-key", message);
    }

    private void publishLazyRabbit() {
        var message = new CustomMessage(
                UUID.randomUUID(),
                "Message for lazy.rabbit-key",
                Date.from(Instant.now())
        );
        template.convertAndSend(topicExchange.getName(), "lazy.rabbit-key", message);
    }

    private void publishLazy() {
        var message = new CustomMessage(
                UUID.randomUUID(),
                "Message for lazy",
                Date.from(Instant.now())
        );
        template.convertAndSend(topicExchange.getName(), "lazy", message);
    }
}
