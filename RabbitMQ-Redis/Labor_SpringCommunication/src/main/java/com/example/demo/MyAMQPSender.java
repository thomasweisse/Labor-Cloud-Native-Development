package com.example.demo;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;

public class MyAMQPSender {

    @Autowired
    private RabbitTemplate myTemplate;

    @Autowired
    private Queue myQueue;

    @RabbitHandler
    //@Scheduled(fixedDelay = 1000, initialDelay = 500)
    private void sendMessage() {
        String myMessage = "Test 1,2,3";
        this.myTemplate.convertAndSend(myQueue.getName(), myMessage);
        System.out.println(" [x] Sent '" + myMessage + "'");
    }
}
