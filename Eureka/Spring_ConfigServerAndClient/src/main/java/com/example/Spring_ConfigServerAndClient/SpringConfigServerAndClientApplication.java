package com.example.Spring_ConfigServerAndClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigServer
public class SpringConfigServerAndClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringConfigServerAndClientApplication.class, args);
	}

	@RestController
	@RefreshScope
	public class MyConfigServerRestController {

		@Value("${config.property: Default}")
		private String myConfigProperty;

		@RequestMapping("/valueFromServer")
		public String valueFromServer() {
			return this.myConfigProperty;
		}
	}
}
