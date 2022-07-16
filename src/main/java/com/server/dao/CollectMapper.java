package com.server.dao;


import com.server.pojo.Collect;
import com.server.pojo.Song;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface CollectMapper {
     //根据用户id查找对应的收藏
    List<Collect> collectionOfUser(@Param("userId") Integer userId);
    //根据收藏的用户id和歌曲id删除收藏表中对应的信息
    int deleteCollect(@Param("userId") Integer userId, @Param("songId") Integer songId);

}
