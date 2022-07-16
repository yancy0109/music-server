package com.server.dao;

import com.server.pojo.Singer;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author bai
 * @version 1.0
 */
@Repository
public interface SingerMapper {
    //查询所有歌手
    List<Singer> allSinger();

    int addSinger(Singer singer);

    int updateSinger(Singer singer);

    int deleteSingerById(@Param("id")int id);

}
