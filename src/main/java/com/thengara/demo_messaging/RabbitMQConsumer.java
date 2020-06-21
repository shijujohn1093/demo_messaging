package com.thengara.demo_messaging;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@EnableRabbit
public class RabbitMQConsumer {

	@RabbitListener(queues = RabbitMQConfig.queueMobile)
	public void getMessage(Person p){
		System.out.println("I am consumer >>>>>>>>> " +p.getName());
	}

}
