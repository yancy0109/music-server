package com.server.service;

import com.server.pojo.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentService {
    List<Comment> commentOfSongId(Integer songId);

    int deleteComment(Integer id);
}