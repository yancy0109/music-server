package com.server.service.Impl;

import com.server.dao.SongMapper;
import com.server.pojo.Song;
import com.server.service.SongService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SongServiceImpl implements SongService {
    @Resource(name="songMapper")
   private  SongMapper songMapper;


    @Override
    public List<Song> getSongByIf(Map<String, Object> map) {
        return songMapper.getSongByIf(map);
    }

    @Override
    public int updateSong(Song song) {
        return songMapper.updateSong(song);
    }

    @Override
    public int deleteSong(Integer id) {
        return songMapper.deleteSong(id);
    }
}
