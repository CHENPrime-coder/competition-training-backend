package xyz.chenprime.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisTemplateConfig {

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,String> redisTemplateForDailyPunch() {
        RedisTemplate<String,String> template = new RedisTemplate<>();
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setDatabase(0);
        factory.afterPropertiesSet();

        template.setConnectionFactory(factory);

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        String
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
//        hashmap
        template.setHashKeySerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        return  template;
    }

    @Bean
    @SuppressWarnings("all")
    public RedisTemplate<String,String> redisTemplateFor356Punch() {
        RedisTemplate<String,String> template = new RedisTemplate<>();
        LettuceConnectionFactory factory = new LettuceConnectionFactory();
        factory.setDatabase(1);
        factory.afterPropertiesSet();

        template.setConnectionFactory(factory);

        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
//        String
        template.setKeySerializer(redisSerializer);
        template.setValueSerializer(redisSerializer);
//        hashmap
        template.setHashKeySerializer(redisSerializer);
        template.setHashValueSerializer(redisSerializer);
        return  template;
    }

}
