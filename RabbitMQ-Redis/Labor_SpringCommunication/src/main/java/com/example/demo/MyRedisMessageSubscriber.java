package com.example.demo;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyRedisMessageSubscriber implements MessageListener {

    public static List<String> myMessageList =  new ArrayList<String>();

    @Override
    public void onMessage(final Message myMessage, final byte[] myPattern) {
        myMessageList.add(myMessage.toString());
        System.out.println("My message reveived: " + new String(myMessage.getBody()));
        System.out.println("Message received: " + myMessage);
    }

    public String getMyMessages() {
        return myMessageList.toString();
    }
}
