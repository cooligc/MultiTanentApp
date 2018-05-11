package com.skc.multitanent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

/***
 * 
 * @author sitakant
 *
 */
@Configuration
public class RedisConfiguration {

	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory = new JedisConnectionFactory();
	    jedisConFactory.setHostName("10.41.37.40");
	    jedisConFactory.setPort(6379);
	    /*jedisConFactory.setHostName("redis-18859.c16.us-east-1-3.ec2.cloud.redislabs.com");
	    jedisConFactory.setPort(18859);
	    
	    jedisConFactory.setPassword("JMitsBl4WpRuSPpK51vKiYnUWCCzjKKX");*/
	    return jedisConFactory;
	}
	
	
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		final RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
		template.setConnectionFactory(jedisConnectionFactory());
		template.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
		return template;
	}
	
}
