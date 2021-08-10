package com.example.hiservice.service;

import org.springframework.web.bind.annotation.GetMapping;

import com.example.hiservice.controller.Greeting;

import feign.RequestLine;

public interface HelloService {

    @RequestLine("GET /hello")
	public String sayHello();
	
    
    @RequestLine("POST /hello/postdata")
  	public Greeting retrieveSomeData(Greeting greeting);
    
}
