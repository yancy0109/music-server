package com.server.dao;

import com.server.pojo.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SongMapper {

    //查询所有歌曲
    List<Song> getAllSong();
    //通过歌手id查询歌曲
    List<Song> getSongBySingerId(@Param("id") int id);
}
