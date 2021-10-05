package com.example.hiservice.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private SecurityScheme jwtScheme() {
		return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name("JWT").build();
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(List.of(defaultAuth()))
				.operationSelector(o -> o.requestMappingPattern().matches("/.*")).build();
	}

	private SecurityReference defaultAuth() {
		return SecurityReference.builder().scopes(new AuthorizationScope[0]).reference("JWT").build();
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", "Authorization", "header");
	}

	
	@Bean
	   public Docket productApi() {
	      return new Docket(DocumentationType.SWAGGER_2)
	          	    .securityContexts(Arrays.asList(securityContext()))
	          	      .securitySchemes(Arrays.asList(apiKey()))
	    		  .select()
	         .apis(RequestHandlerSelectors.basePackage("com.example.hiservice.controller")).build();
	   }
	
	
	/*
	@Bean
    public Docket api() { 
		
		/*return new Docket(DocumentationType.SWAGGER_2)  
        	//    .securityContexts(Arrays.asList(securityContext()))
        	//      .securitySchemes(Arrays.asList(apiKey()))
          .select()                                  
          .apis(RequestHandlerSelectors.any())              
          .paths(PathSelectors.any())
          .build();                                       
    }*/

}