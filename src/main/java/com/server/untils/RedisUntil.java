package com.server.untils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

import java.time.Duration;

public class RedisUntil {
    //Spring RedisTemplate
    private static RedisTemplate<String, Object> redisTemplate;

    //设置默认Redis过期时间
    private final static Duration OUT_TIME = Duration.ofHours(1);

    //通过构造方法注入RedisTemplate
    public RedisUntil(RedisConnectionFactory redisConnectionFactory)
    {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        redisTemplate = template;
    }

    //获取对象
    public Object getObjecet(String key){
        return redisTemplate.opsForValue().get(key);
    }
    //获取Token
    public Object getToken(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     *保存对象
     * @param key
     * @param value
     * @param timeout
     */
    public void setObject(String key, Object value, Duration timeout){
         redisTemplate.opsForValue().set(key, value,timeout);
    }

    /**
     * 保存对象 默认过期时间
     * @param key
     * @param value
     */
    public void setObjectDefault(String key, Object value){
         redisTemplate.opsForValue().set(key, value,OUT_TIME);
    }

    /**
     * 存储token
     * @param key
     * @param value
     */
    public void setToken(String key, Object value){
         redisTemplate.opsForValue().set(key, value,OUT_TIME);
    }

    /**
     * 保存对象 无过期时间
     * @param key
     * @param value
     */
    public void setObject(String key, Object value){
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 移除redis对象
     * @param key
     * @return
     */
    public Boolean removeObject(String key){
        return redisTemplate.delete(key);
    }

    /**
     * 传入username
     * 清楚Redis保存的相关信息
     * @param username
     * @return
     */
    public boolean removeUser(String username){
        if ( redisTemplate.opsForValue().get(username) == null){
            return true;
        }
        String sessionId = redisTemplate.opsForValue().get(username).toString();
        return redisTemplate.delete(sessionId) && redisTemplate.delete(username);
    }

    /**
     * 存储用户信息
     * @param username
     * @param sessionId
     * @param token
     * @return
     */
    public void addUser(String username,String sessionId,String token){
        redisTemplate.opsForValue().set(username,sessionId);
        redisTemplate.opsForValue().set(sessionId,token);
    }
}
