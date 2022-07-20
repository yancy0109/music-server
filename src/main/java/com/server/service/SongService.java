package com.server.service;

import com.server.pojo.Song;
import java.util.List;
import java.util.Map;

public interface SongService {

    List<Song> getSongByIf(Map<String,Object> map);
    int updateSong(Song song);
    int deleteSong(Integer id);
    int addSong(Song song);

}
