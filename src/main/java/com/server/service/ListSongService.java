package com.server.service;

import com.server.pojo.ListSong;

import java.util.List;

public interface ListSongService {

    //给歌单添加歌曲
    boolean addListSong(ListSong listSong);

    //删除歌单里的歌曲
    boolean deleteListSong(Integer songId);

    //返回歌单里指定歌单ID的歌曲
    List<ListSong> listSongOfSongId(Integer songListId);
}
