package com.server.service.Impl;

import com.server.dao.CollectMapper;
import com.server.pojo.Collect;
import com.server.pojo.Song;
import com.server.service.CollectService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component("collectServiceImpl")
public class CollectServiceImpl implements CollectService {
    private CollectMapper collectMapper;
    @Resource(name="collectMapper")
    public void setCollectMapper(CollectMapper collectMapper) {
        this.collectMapper = collectMapper;
    }

    @Override
    public List<Collect> collectionOfUser(Integer userId) {
        return  collectMapper.collectionOfUser(userId);
    }

    @Override
    public int deleteCollect(Integer userId, Integer songId) {
        return collectMapper.deleteCollect(userId,songId);
    }
}
