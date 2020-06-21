package com.thengara.demo_messaging;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	private static final String rabbitMQHostname = "localhost";

	private static final int rabbitMQPort = 5672;
	
	public static final String testDirectExchange = "test-direct-exchange";


	public static final String queueName = "test-queue-1";

	public static final String queueAC = "test-queue-ac";

	public static final String queueTV = "test-queue-tv";

	public static final String queueMobile = "test-queue-mobile";

	@Bean
	public ConnectionFactory connectionFactory() {
		ConnectionFactory factory = new CachingConnectionFactory(rabbitMQHostname, rabbitMQPort);
		return factory;
	}

	@Bean
	public RabbitTemplate rabbitTemplate() {
		return new RabbitTemplate(connectionFactory());
	}

	@Bean
	public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
		SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
		factory.setConnectionFactory(connectionFactory());
		return factory;
	}
}
