package com.example.spring_hystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableCircuitBreaker
public class MyCircuitBreakerApp {

    public static void main(String[] args) {
        SpringApplication.run(MyCircuitBreakerApp.class, args);
    }

    @Service
    class MyPotentiallyFailingService {

        public int myFallback() {
            return 2;
        }

        @HystrixCommand(fallbackMethod = "myFallback")
        public int getANumber() throws Exception {
            if(Math.random() > .5) {
                Thread.sleep(1000);
                throw new RuntimeException("The service has failed.");
            }
            return 1;
        }
    }

    @RestController
    class MyConfigFileRestController {

        private final MyPotentiallyFailingService myFailingService;

        @Autowired
        public MyConfigFileRestController(MyPotentiallyFailingService myFailingService) {
            this.myFailingService = myFailingService;
        }

        @RequestMapping("/service")
        public String myService() throws Exception {
            return "Number of the service: " + this.myFailingService.getANumber();
        }
    }
}
