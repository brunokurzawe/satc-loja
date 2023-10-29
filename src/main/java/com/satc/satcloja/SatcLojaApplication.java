package com.satc.satcloja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.time.Duration;

@EnableWebMvc
@SpringBootApplication
public class SatcLojaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatcLojaApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*");
            }
        };
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(jedisConnectionFactory());
        return template;
    }

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName("oregon-redis.render.com");
        redisConfig.setPort(6379);
        redisConfig.setPassword("ZO8w8y9nTNPTIcFtH34Xqn02Ks88tPsj");
        redisConfig.setUsername("red-ckv5pga37rbc73es4hhg");
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfig = JedisClientConfiguration.builder();
        jedisClientConfig.connectTimeout(Duration.ofSeconds(60));
        jedisClientConfig.useSsl();

        return new JedisConnectionFactory(redisConfig, jedisClientConfig.build());
    }
}
