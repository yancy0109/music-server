package com.server.service.Impl;

import com.server.dao.SongMapper;
import com.server.pojo.Song;
import com.server.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongServiceImpl implements SongService {

    @Autowired
    SongMapper songMapper;

    @Override
    public List<Song> getAllSong() {
        return songMapper.getAllSong();
    }

    @Override
    public List<Song> getSongBySingerId(int id) {
        return songMapper.getSongBySingerId(id);
    }
}
