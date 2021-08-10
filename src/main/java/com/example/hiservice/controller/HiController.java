package com.example.hiservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.hiservice.service.HelloService;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;

//import com.netflix.appinfo.InstanceInfo;
//import com.netflix.discovery.EurekaClient;


@RestController
@RequestMapping("/hi")
public class HiController  {
	
	@GetMapping
	public String sayHello() {
		return "hi";
	}
	
//	@Autowired
//	private EurekaClient client;
	
	@GetMapping("/greeting")
	public String sayGreeting() {
//		InstanceInfo instance=client.getNextServerFromEureka("HELLOSERVICE", false);
		
		
/*		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response=restTemplate.exchange(instance.getHomePageUrl()+"/hello", 
									HttpMethod.GET, null,  
									new ParameterizedTypeReference<String>() {});
						
		
		String body=response.getBody();
		*/
		
		HelloService helloService = Feign.builder()
				  .client(new OkHttpClient())
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .target(HelloService.class, "http://localhost:8150");
		return helloService.sayHello();
		
		
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