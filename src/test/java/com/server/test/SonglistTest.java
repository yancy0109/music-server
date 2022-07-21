package com.server.test;

import com.server.pojo.SongList;
import com.server.service.SongListService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SonglistTest {
    @Test
    public void demo1(){
        System.out.println("hello你好!");
    }
    @Test
    public void demo2(){

        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        SongListService service = context.getBean("songListServiceImpl", SongListService.class);
        List<SongList> allSongList = service.allSongList();
        for (SongList songList : allSongList) {
            System.out.println(songList);
        }
    }
}
