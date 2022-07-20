package com.server.test;
import com.server.untils.RedisUntil;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RedisTest {
    RedisUntil redisUntil;
    @Before
    public void before(){
        redisUntil = new ClassPathXmlApplicationContext("spring-redis.xml").getBean(RedisUntil.class);
    }
    @Test
    public void test1(){
        redisUntil.setObject("sda","sda");
        System.out.println(redisUntil.getToken("sda"));
    }

}
