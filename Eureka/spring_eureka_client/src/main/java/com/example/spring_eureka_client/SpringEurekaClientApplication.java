package com.example.spring_eureka_client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@EnableEurekaClient
@SpringBootApplication
public class SpringEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaClientApplication.class, args);
	}

	@RestController
	public class MyConfigFileRestController {

		@RequestMapping("/serviceThomas")
		public String serviceThomas() {
			return "My service exposed to Eureka.";
		}

		@RequestMapping("/RasidsWorld")
		public String myHelloWorld() {
			return "Hallo Welt, mein Name ist Rasid!";
		}

		@Bean
		@LoadBalanced
		public RestTemplate myRestTemplate() {
			return new RestTemplate();
		}

		@Autowired
		private RestTemplate myRest;

		@RequestMapping("/serviceViaMyTemplate")
		public String invokeMyService() {
			return this.myRest.getForObject("//thomas-service/service", String.class);
		}
	}
}
