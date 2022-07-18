package com.server.dao;

import com.server.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CommentMapper {
    //根据传入的歌曲id查找对应的评论
    List<Comment> commentOfSongId(@Param("songId") Integer songId);
     int deleteComment(@Param("id") Integer id);
     int addComment(Comment comment);
     List<Comment> getAllComment(Map<String,Object> map);
}
