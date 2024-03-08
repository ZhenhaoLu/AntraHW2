package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableAsync
public class DemoApplication {

	private final RestTemplateBuilder builder;

	@Autowired
	public DemoApplication(RestTemplateBuilder builder){
		this.builder = builder;
	}

	@Bean
	public RestTemplate restTemplate(){
//		return new RestTemplateBuilder().build();
		return builder.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
