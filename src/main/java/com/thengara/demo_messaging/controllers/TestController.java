package com.thengara.demo_messaging.controllers;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.thengara.demo_messaging.Person;
import com.thengara.demo_messaging.RabbitMQConfig;

@RestController
@RequestMapping("/api/v1")
public class TestController {

	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
	public String testAPI(@PathVariable("name") String name){
		Person p = new Person(1L, name);
		rabbitTemplate.convertAndSend(RabbitMQConfig.queueMobile, p);
		rabbitTemplate.convertAndSend(RabbitMQConfig.testDirectExchange, "mobile", p);
	
		return "Success";
	}
	
	/*@RequestMapping(value = "/test/{name}", method = RequestMethod.GET)
	public String testAPI(@PathVariable("name") String name) throws IOException{
		Person p = new Person(1L, name);
		
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutput out = new ObjectOutputStream(bos);
		out.writeObject(p);
		out.flush();
		out.close();
		
		byte[] byteMessage = bos.toByteArray();
		bos.close();
		
		Message message = MessageBuilder.withBody(byteMessage)
				.setHeader("item1", "mobile")
				.setHeader("item2", "television").build();
		
		rabbitTemplate.send("Headers-Exchange", "", message);
		
		return "Success";
	}*/
}
