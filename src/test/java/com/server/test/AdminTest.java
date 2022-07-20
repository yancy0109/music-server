package com.server.test;

import com.server.dao.AdminMapper;
import com.server.pojo.Song;
import com.server.service.AdminService;
import com.server.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdminTest {

    AdminService adminService;
    AdminMapper adminMapper;
    @Before
    public void before(){
        adminMapper = new ClassPathXmlApplicationContext("spring-test.xml").getBean(AdminMapper.class);
        adminService = new ClassPathXmlApplicationContext("spring-test.xml").getBean(AdminService.class);
    }

    @Test
    public void test1(){
//        System.out.println(adminService.checkAdmin("123","1231"));
        System.out.println(adminMapper);
        System.out.println(adminService.checkAdmin("1223","1223"));
    }
}
