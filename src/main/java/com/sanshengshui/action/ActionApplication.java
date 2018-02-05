package com.sanshengshui.action;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sanshengshui.action.common.bean.CustomRepositoryFactoryBean;
import com.sanshengshui.action.dao.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.net.UnknownHostException;

@EnableJpaRepositories(repositoryFactoryBeanClass =
		CustomRepositoryFactoryBean.class)
@SpringBootApplication
public class ActionApplication {
	@Bean
	@SuppressWarnings({"rawtypes","unchecked"})
	public RedisTemplate<Object,Object> redisTemplate(RedisConnectionFactory redisConnectionFactory)
	throws UnknownHostException{
		RedisTemplate<Object,Object> template = new RedisTemplate<Object,Object>();
		template.setConnectionFactory(redisConnectionFactory);
		Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new
				Jackson2JsonRedisSerializer(Object.class);
		ObjectMapper om = new ObjectMapper();
		om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
		om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
		jackson2JsonRedisSerializer.setObjectMapper(om);
		template.setValueSerializer(jackson2JsonRedisSerializer);
		template.setKeySerializer(new StringRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}
	@Autowired
	PeopleRepository peopleRepository;

	public static void main(String[] args) {
		SpringApplication.run(ActionApplication.class, args);
	}
}
