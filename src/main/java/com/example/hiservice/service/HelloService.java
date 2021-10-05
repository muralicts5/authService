package com.example.hiservice.service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

import com.example.hiservice.controller.Greeting;

import feign.RequestLine;

public interface HelloService {

    @RequestLine("GET /hello")
	public String sayHello(@RequestHeader("Authorization") String token);
	
    
    @RequestLine("POST /hello/postdata")
  	public Greeting retrieveSomeData(Greeting greeting);
    
}
