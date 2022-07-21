package com.server.service;

import com.server.pojo.SongList;

import java.util.List;

public interface SongListService {
    //获取全部歌单
    List<SongList> allSongList();

    //添加歌单
    boolean addSongList (SongList songList);

    //更新歌单信息
    boolean updateSongListMsg(SongList songList);

    //更新歌单图片
    boolean updateSongListImg(SongList songList);

    //删除歌单
    boolean deleteSongList(Integer id);
}
