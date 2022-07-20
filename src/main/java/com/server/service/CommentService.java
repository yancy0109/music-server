package com.server.service;

import com.server.pojo.Comment;
import java.util.List;
import java.util.Map;

public interface CommentService {
    List<Comment> commentOfSongId(Integer songId);

    int deleteComment(Integer id);
    int addComment(Comment comment);
    List<Comment> getAllComment(Map<String,Object> map);
}