package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
@EnableScheduling
public class MySpringAMQPApplication {

    public static void main(String[] args) {
        SpringApplication.run(MySpringAMQPApplication.class, args);
    }

    @GetMapping("/getInformation")
    public String getString() {
        return "Dies ist ein Teststring!";
    }

    public String Service() {
        RestTemplate myRestTemplate = new RestTemplate();
        String myURL = "http://localhost:8080/getInformation";

        ResponseEntity<String> responseEntity = myRestTemplate.getForEntity(myURL, String.class);

        return responseEntity.getBody();
    }
    /*
    @Autowired
    MyAMQPSender sender;

    @Autowired
    MyAMQPReceiver receiver;*/

}
