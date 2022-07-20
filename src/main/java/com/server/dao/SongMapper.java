package com.server.dao;

import com.server.pojo.Song;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface SongMapper {

   //根据条件查询歌曲
    List<Song> getSongByIf(Map<String,Object> map);
    int updateSong(Song song);
    int deleteSong(@Param("id") Integer id);
    int addSong(Song song);

}
