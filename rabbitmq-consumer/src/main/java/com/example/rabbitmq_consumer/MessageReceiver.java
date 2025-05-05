package com.example.rabbitmq_consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class MessageReceiver {
    private static final Logger log = Logger.getLogger(MessageReceiver.class.getName());

    @RabbitListener(queues = "queue-hello")
    public void receive(CustomMessage message) {
        log.info("Received message: " + message);
    }
}
