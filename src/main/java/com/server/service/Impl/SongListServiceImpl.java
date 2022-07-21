package com.server.service.Impl;

import com.server.dao.SongListMapper;
import com.server.pojo.SongList;
import com.server.service.SongListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SongListServiceImpl implements SongListService {
    @Resource(name="songListMapper")
    private SongListMapper songListMapper;

    //更新歌单信息
    @Override
    public boolean updateSongListMsg(SongList songList) {
        return songListMapper.updateSongListMsg(songList) >0;
    }
    //删除歌单
    @Override
    public boolean deleteSongList(Integer id) {
        return songListMapper.deleteSongList(id) >0;
    }
    //获取全部歌单
    @Override
    public List<SongList> allSongList() {
        return songListMapper.allSongList();
    }
    //添加歌单
    @Override
    public boolean addSongList(SongList songList) {
        return songListMapper.insertSelective(songList) > 0;
    }
    //更新歌单图片
    @Override
    public boolean updateSongListImg(SongList songList) {
        return songListMapper.updateSongListImg(songList) >0;
    }
}
