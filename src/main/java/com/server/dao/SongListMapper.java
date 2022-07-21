package com.server.dao;

import com.server.pojo.SongList;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface SongListMapper {

    //int insert(SongList record);

    int insertSelective(SongList record);

    int updateSongListMsg(SongList record);

    int updateSongListImg(SongList record);

    int deleteSongList(Integer id);

    List<SongList> allSongList();

}
