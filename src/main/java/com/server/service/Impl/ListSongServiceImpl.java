package com.server.service.Impl;

import com.server.dao.ListSongMapper;
import com.server.pojo.ListSong;
import com.server.service.ListSongService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

//@Component("listSongServiceImpl")
@Service
public class ListSongServiceImpl implements ListSongService {

    //@Autowired
    @Resource(name="listSongMapper")
    private ListSongMapper listSongMapper;


    @Override
    public boolean deleteListSong(Integer songId) {
        return listSongMapper.deleteListSong(songId) > 0;
    }

    @Override
    public boolean addListSong(ListSong listSong)
    {
        return listSongMapper.insertSelective(listSong) > 0;
    }

    @Override
    public List<ListSong> listSongOfSongId(Integer songListId)
    {
        return listSongMapper.listSongOfSongId(songListId);
    }

}
