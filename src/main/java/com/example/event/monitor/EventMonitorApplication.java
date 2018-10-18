package com.example.event.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.event.monitor.db.EventCounterService;
import com.example.event.monitor.db.EventCounterServiceImpl;
import com.example.event.monitor.service.MessagePublisher;
import com.example.event.monitor.service.RedisMessagePublisher;
import com.example.event.monitor.service.RedisMessageSubscriber;
import com.example.event.monitor.streams.EventMonitorStreams;
import com.threedsoft.util.service.EventPublisher;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(EventMonitorStreams.class)
@EnableAutoConfiguration
@EnableScheduling
//@EnableMongoAuditing
@EnableRedisRepositories
@Slf4j
public class EventMonitorApplication {
	@Autowired
	EventMonitorStreams eventMonitorStreams;

    @Value("${redis.host}")
	private String redisHost;
    @Value("${redis.port}")
	private Integer redisPort;
	
	public static void main(String[] args) {
		SpringApplication.run(EventMonitorApplication.class, args);
	}

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(redisHost);
		jedisConFactory.setPort(redisPort);
		return jedisConFactory;
		//return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
        //redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
       // redisTemplate.setValueSerializer(new GenericToStringSerializer<Long>(Long.class));
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}
	
	@Bean
	EventCounterService eventCounterService() { 
	    return new EventCounterServiceImpl(redisTemplate());
	}

	@Bean
	MessageListenerAdapter messageListener() { 
	    return new MessageListenerAdapter(new RedisMessageSubscriber(eventCounterService()));
	}
	
	@Bean
	RedisMessageListenerContainer redisContainer() {
	    RedisMessageListenerContainer container 
	      = new RedisMessageListenerContainer(); 
	    container.setConnectionFactory(jedisConnectionFactory()); 
	    container.addMessageListener(messageListener(), topic()); 
	    return container; 
	}
	
	@Bean
	MessagePublisher redisPublisher() { 
	    return new RedisMessagePublisher(redisTemplate(), topic());
	}
	
	@Bean
	ChannelTopic topic() {
	    return new ChannelTopic("messageQueue");
	}	
}
