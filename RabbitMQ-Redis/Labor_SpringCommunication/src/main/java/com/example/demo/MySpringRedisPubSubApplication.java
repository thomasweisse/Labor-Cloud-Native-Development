package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MySpringRedisPubSubApplication {

    @Autowired
    MyRedisMessagePublisher myRedisMessagePublisher;

    @Autowired
    MyRedisMessageSubscriber myRedisMessageSubscriber;

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(RedisAppConfig.class);
        //SpringApplication.run(MySpringRedisPubSubApplication.class, args);
    }
}
