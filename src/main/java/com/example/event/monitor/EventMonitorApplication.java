package com.example.event.monitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.example.event.monitor.streams.EventMonitorStreams;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableBinding(EventMonitorStreams.class)
@EnableAutoConfiguration
@EnableScheduling
@EnableJpaAuditing
@EntityScan(
        basePackageClasses = {EventMonitorApplication.class, Jsr310JpaConverters.class}
)
@Slf4j
public class EventMonitorApplication {
	@Autowired
	EventMonitorStreams eventMonitorStreams;

/*	@Value("${redis.host}")
	private String redisHost;
	@Value("${redis.port}")
	private Integer redisPort;
*/
	public static void main(String[] args) {
		SpringApplication.run(EventMonitorApplication.class, args);
	}

/*	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
		JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
		jedisConFactory.setHostName(redisHost);
		jedisConFactory.setPort(redisPort);
		return jedisConFactory;
		// return new JedisConnectionFactory();
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		// redisTemplate.setHashValueSerializer(new
		// GenericJackson2JsonRedisSerializer());
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		// redisTemplate.setValueSerializer(new
		// GenericToStringSerializer<Long>(Long.class));
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		// redisTemplate.setHashValueSerializer(new
		// StringRedisSerializer(StandardCharsets.UTF_8));
		redisTemplate.setHashValueSerializer(new GenericToStringSerializer<Long>(Long.class));
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

	@Bean
	EventCounterService eventCounterService() {
		return new EventCounterServiceImpl(redisTemplate());
	}

	@Bean
	OrderEventCounterService orderEventCounterService() {
		return new OrderEventCounterServiceImpl(redisTemplate());
	}

	@Bean
	PickEventCounterService pickEventCounterService() {
		return new PickEventCounterServiceImpl(redisTemplate());
	}

	@Bean
	MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new RedisMessageSubscriber(eventCounterService(), pickEventCounterService(),
				orderEventCounterService()));
	}

	@Bean
	RedisMessageListenerContainer redisContainer() {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
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

*/	@Bean
	public CorsFilter corsFilter() {
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true); 
	    config.addAllowedOrigin("http://*the3dsoft.com");
	    config.addAllowedOrigin("http://localhost");
	    config.addAllowedOrigin("https://localhost:5000");
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod("*");
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}
