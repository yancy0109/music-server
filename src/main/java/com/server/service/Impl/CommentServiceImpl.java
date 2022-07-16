package com.server.service.Impl;

import com.server.dao.CommentMapper;
import com.server.pojo.Comment;
import com.server.service.CommentService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
@Component("commentServiceImpl")
public class CommentServiceImpl implements CommentService {
    private CommentMapper commentMapper;
    @Resource(name="commentMapper")
    public void setCommentMapper(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    @Override
    public List<Comment> commentOfSongId(Integer songId) {
        return commentMapper.commentOfSongId(songId);
    }

    @Override
    public int deleteComment(Integer id) {
        return commentMapper.deleteComment(id);
    }
}
