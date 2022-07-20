package com.server.test;


import com.server.pojo.Consumer;
import com.server.service.ConsumerService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class ConsumerTest {
    ConsumerService consumerService;

    @Before
    public void before(){
        consumerService = new ClassPathXmlApplicationContext("spring-test.xml").getBean(ConsumerService.class);
    }

    @Test
    public void getAllUser(){
        List<Consumer> allUser = consumerService.getAllUser();
        for (Consumer consumer : allUser) {
            System.out.println(consumer);
        }
    }

    @Test
    public void getUserById(){
        List<Consumer> userById = consumerService.getUserById(1);
        for (Consumer consumer : userById) {
            System.out.println(consumer);
        }
    }

    @Test
    public void addUser(){
        Consumer consumer = new Consumer();
        consumer.setUsername("Hah");
        consumer.setPassword("sda");
        consumer.setCreateTime(new Date());
        consumer.setUpdateTime(new Date());
        boolean b = consumerService.addUser(consumer);
        System.out.println(b);
    }

    @Test
    public void deleteUser(){
        boolean flag = consumerService.deleteUserById(30);
        System.out.println(flag);
    }

    @Test
    public void updateUser(){
        List<Consumer> userById = consumerService.getUserById(28);
        Consumer consumer = userById.get(0);
        System.out.println(consumer.toString());
        consumer.setUsername("yoona91");
        consumerService.updateUser(consumer);

        consumer = consumerService.getUserById(28).get(0);
        System.out.println(consumer.toString());

    }
}
