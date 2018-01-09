package com.example.demo;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@RabbitListener(queues = "this is my own queue")
public class MyAMQPReceiver {

    @RabbitHandler
    private void receiveMessage(String input) {
        System.out.println("Received '" + input + "'");
    }
}
