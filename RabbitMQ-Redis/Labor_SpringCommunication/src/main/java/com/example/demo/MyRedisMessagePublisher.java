package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;

@Service
public class MyRedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String, Object> myRedisTemplate;

    @Autowired
    private ChannelTopic myTopic;

    public MyRedisMessagePublisher(final RedisTemplate<String, Object> myRedisTemplate, final ChannelTopic myTopic) {
        this.myRedisTemplate = myRedisTemplate;
        this.myTopic = myTopic;
    }

    public void myPublish(final String myMessage) {
        myRedisTemplate.convertAndSend(myTopic.getTopic(), myMessage);
    }
}
