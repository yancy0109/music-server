package com.server.test;

import com.server.pojo.Song;
import com.server.service.ConsumerService;
import com.server.service.SongService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class SongTest {
    SongService songService;
    @Before
    public void before(){
        songService = new ClassPathXmlApplicationContext("spring-test.xml").getBean(SongService.class);
    }

    @Test
    public void getAllSong(){
        List<Song> allSong = songService.getAllSong();
        for (Song song : allSong) {
            System.out.println(song);
        }
    }

    @Test
    public void getSongBySingerId(){
        List<Song> songBySingerId = songService.getSongBySingerId(1);
        for (Song song : songBySingerId) {
            System.out.println(song);
        }

    }
}
