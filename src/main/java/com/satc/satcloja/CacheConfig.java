package com.satc.satcloja;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(2)); // Define a duração do cache para 2 minutos

        return RedisCacheManager.builder(jedisConnectionFactory)
                .cacheDefaults(config)
                .build();
    }
}