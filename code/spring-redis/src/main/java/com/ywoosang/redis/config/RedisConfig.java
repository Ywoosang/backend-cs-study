package com.ywoosang.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Value("${spring.data.redis.host}")
    private String host;

    @Value("${spring.data.redis.port}")
    private int port;

    /**
     *
     *
     * RedisConnection은 RedisConnectionFactory를 통해 생성되고
     * RedisConnectionFactory가 PersistenceExceptionTranslator 역할을 수행
     *
     * IOC 컨테이너를 통해 RedisConnectionFactory에 적절한 Connector를 설정하고 이를 주입받아서 사용
     *
     * Redis Connector에는 대표적으로 Lettuce와 Jedis -> Lettuce 사용
     *
     *
     */

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory(getConfig());
    }


    @Bean
    public RedisTemplate<String, String> redisTemplate() {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    private RedisStandaloneConfiguration getConfig() {
        var config = new RedisStandaloneConfiguration();
        config.setHostName(host);
        config.setPort(port);
        //        config.setPassword("password"); 비번 설정
        //        config.setDatabase(0); 데이터베이스 인덱스 설정
        return config;
    }

}
