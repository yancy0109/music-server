package com.server.service.Impl;

import com.server.dao.CommentMapper;
import com.server.pojo.Comment;
import com.server.service.CommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("commentServiceImpl")
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

    @Override
    public int addComment(Comment comment) {
        return  commentMapper.addComment(comment);
    }

    @Override
    public List<Comment> getAllComment(Map<String, Object> map) {

       List<Comment> commentList=commentMapper.getAllComment(map);
       System.out.println("在Service中的查询结果：");
       System.out.println(commentList);
       return null;
    }
}
