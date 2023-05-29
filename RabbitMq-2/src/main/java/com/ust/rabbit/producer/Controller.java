package com.ust.rabbit.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ust.rabbit.model.entity;

@RestController
@RequestMapping("/cr")
public class Controller {
	@Autowired
	private RabbitTemplate template;
	
	@PostMapping("/create")
	public String create(@RequestBody entity ent) {
		template.convertAndSend("oexchange","orouting_key",ent);
		return "done";
		
	}
	

}
