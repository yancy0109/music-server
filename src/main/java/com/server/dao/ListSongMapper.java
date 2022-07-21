package com.server.dao;

import com.server.pojo.ListSong;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ListSongMapper {

    //添加歌单的歌曲
    int insertSelective(ListSong record);

    //删除歌单里的歌曲
    int deleteListSong(Integer songId);

    //返回歌单里指定歌单ID的歌曲
    List<ListSong> listSongOfSongId(Integer songListId);
}
