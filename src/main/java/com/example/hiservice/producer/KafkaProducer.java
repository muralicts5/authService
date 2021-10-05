package com.example.hiservice.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;


	public void sendMessage(String message) {
		System.out.println("inside the send message");
		kafkaTemplate.send("greeting-topic", 0, null, message);
	}


}
