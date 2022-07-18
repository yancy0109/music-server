package com.server.controller;


import com.server.common.SuccessMessage;
import com.server.pojo.Song;
import com.server.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SongController {

    @Autowired
    SongService songService;

    @RequestMapping("/song")
    public Object getAllSong(){
        return new SuccessMessage<List<Song>>("查询成功",songService.getAllSong()).getMessage();
    }

    @RequestMapping("song/singer/detail")
    public Object getSongBySingerId(@RequestParam("singerId")int id){
        return new SuccessMessage<List<Song>>("查询成功",songService.getSongBySingerId(id)).getMessage();
    }
}
