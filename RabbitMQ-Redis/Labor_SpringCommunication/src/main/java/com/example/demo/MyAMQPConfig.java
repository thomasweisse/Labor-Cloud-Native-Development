package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class MyAMQPConfig {

    @Bean
    public Queue hello() {
        return new Queue("this is my own queue");
    }

    @Profile("receiver")
    @Bean
    public MyAMQPReceiver receiver() {
        return new MyAMQPReceiver();
    }

    @Profile("sender")
    @Bean
    public MyAMQPSender sender() {
        return new MyAMQPSender();
    }

}
