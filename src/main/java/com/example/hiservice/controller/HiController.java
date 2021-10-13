package com.example.hiservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hiservice.model.Employee;
import com.example.hiservice.producer.KafkaProducer;
import com.example.hiservice.service.HelloService;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;


@RestController
@RequestMapping("/hi")
@CrossOrigin("*")
public class HiController  {
	
	@Autowired
	private KafkaProducer producer;
	
	@GetMapping
	public String sayHello() {
		return "hi";
	}
	
	
	@GetMapping("/employees")
	public List<Employee> sayHelloEmployee() {
		Employee e=new Employee();
		e.setName("ramesh");
		e.setDesignation("manager");
		e.setEmail("email.com");
		e.setEmployeeId(1001);
		e.setSalary(3453);
		List<Employee> employees=new ArrayList<Employee>();
		employees.add(e);
		return employees;
	}
	
	
	@GetMapping("/greeting")
	public String sayGreeting(@RequestHeader("Authorization") String token) {
		System.out.println(token);
		HelloService helloService = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .target(HelloService.class, "http://localhost:8150");
		return helloService.sayHello(token);
	}
	
	@GetMapping("/async")
	public String sayHelloAsync(@RequestHeader("Authorization") String token) {
		producer.sendMessage("hello asyn message from hi service");
		return "message sent";
	}
	
	@GetMapping("/getdata")
	public Greeting getPayload() {
		Greeting greeting=new Greeting();
		HelloService helloService = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .target(HelloService.class, "http://localhost:8150");
		return helloService.retrieveSomeData(greeting);
	}
}