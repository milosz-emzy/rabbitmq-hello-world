package com.example.rabbitmq_producer;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RabbitmqMessagePublisherApplicationTests {

	@Autowired
	MessagePublisher messagePublisher;

	@Test
	void contextLoads() {
		messagePublisher.publish();
	}

}
