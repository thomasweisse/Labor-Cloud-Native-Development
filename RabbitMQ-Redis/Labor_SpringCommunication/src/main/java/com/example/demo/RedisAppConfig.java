package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisAppConfig {

    @Bean
    JedisConnectionFactory myJedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public RedisTemplate<String, Object> myRedisTemplate() {
        final RedisTemplate<String, Object> myTemplateRedis = new RedisTemplate<String, Object>();
        myTemplateRedis.setConnectionFactory(myJedisConnectionFactory());
        myTemplateRedis.setKeySerializer( new StringRedisSerializer() );
        myTemplateRedis.setHashValueSerializer( new GenericToStringSerializer< Object >( Object.class ) );
        myTemplateRedis.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        myTemplateRedis.setEnableTransactionSupport(true);
        return myTemplateRedis;
    }

    @Bean
    MessageListenerAdapter myMessageListener() {
        return new MessageListenerAdapter(new MyRedisMessageSubscriber());
    }

    @Bean
    RedisMessageListenerContainer myRedisContainer() {
        final RedisMessageListenerContainer myContainer = new RedisMessageListenerContainer();
        myContainer.setConnectionFactory(myJedisConnectionFactory());
        myContainer.addMessageListener(myMessageListener(), topic());
        return myContainer;
    }

    @Bean
    MessagePublisher myRedisPublisher() {
        return new MyRedisMessagePublisher(myRedisTemplate(), topic());
    }

    @Bean
    ChannelTopic topic() {
        return new ChannelTopic("pubsub:queue");
    }
}
