package com.server.service.Impl;

import com.server.dao.SingerMapper;
import com.server.pojo.Singer;
import com.server.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bai
 * @version 1.0
 */
@Service
public class SingerServiceImpl implements SingerService {


    @Autowired
    private SingerMapper singerMapper;
    @Override
    public List<Singer> allSinger() {
        return singerMapper.allSinger();
    }

    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.addSinger(singer) > 0;
    }

    @Override
    public boolean updateSinger(Singer singer) {
        return singerMapper.updateSinger(singer) > 0;
    }

    @Override
    public boolean deleteSingerById(int id) {
        return singerMapper.deleteSingerById(id)>0;
    }

    @Override
    public boolean updateSingerPic(Singer singer) {
        return singerMapper.updateSingerPic(singer) >0;
    }
}
