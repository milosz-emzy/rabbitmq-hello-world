package com.example.rabbitmq_producer;

import java.util.Date;
import java.util.UUID;

public record CustomMessage(
        UUID id,
        String message,
        Date date
) {
}