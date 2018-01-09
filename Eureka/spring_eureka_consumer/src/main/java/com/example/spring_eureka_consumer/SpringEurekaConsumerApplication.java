package com.example.spring_eureka_consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class SpringEurekaConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringEurekaConsumerApplication.class, args);
	}

	@RestController
	class MyServiceInstanceRestController {

		@Autowired
		private DiscoveryClient myDiscoveryClient;

		@RequestMapping("/thomasServiceList")
		public String myServiceList() {
			return this.myDiscoveryClient.getServices().toString();
		}

		@RequestMapping("/service-instances/{application-name}")
		public List<ServiceInstance> myServiceInstanceByApplicationName(@PathVariable String applicationName) {
			return this.myDiscoveryClient.getInstances(applicationName);
		}
	}

	/*
	@FeignClient("thomas-service")
	interface ServiceClient {
		@RequestMapping(value = "/service", method = RequestMethod.GET);
		String service();
	}

	@Autowired
	private ServiceClient myServiceClient;

	@RequestMapping("/serviceFeign")
	public String invokeService() {
		return this.myServiceClient.service();
	}*/
}
