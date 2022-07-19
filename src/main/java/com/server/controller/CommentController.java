package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.Comment;
import com.server.service.CommentService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class CommentController {
    @Resource(name="commentServiceImpl")
    private CommentService commentService;
    //查找歌曲对应的评论
    @RequestMapping(value = "/comment/song/detail",method = RequestMethod.GET)
    public Object getCommentOfSongId( HttpServletRequest request){
        Integer songId=Integer.parseInt(request.getParameter("songId"));
        List<Comment> commentList= commentService.commentOfSongId(songId);
        return new SuccessMessage<>(null,commentList).getMessage();
    }
    //根据id删除评论
    @RequestMapping(value = "/comment/delete",method = RequestMethod.GET)
    public Object deleteComment (HttpServletRequest request){
        Integer id=Integer.parseInt(request.getParameter("id"));
        int res=commentService.deleteComment(id);
        if(res>0) return new SuccessMessage<>("删除成功！",false).getMessage();
        else return  new ErrorMessage("删除失败！").getMessage();

    }
    @RequestMapping(value = "/comment/add",method = RequestMethod.POST)
    public Object setComment(Comment comment){
        comment.setCreateTime(new Date());

        comment.setCreateTime(new Date());
        int res = commentService.addComment(comment);
        if (res>0) {
            return new SuccessMessage<ObjectUtils.Null>("评论成功",null).getMessage();
        } else {
            return new ErrorMessage("评论失败").getMessage();
        }

    }
    @RequestMapping(value = "/comment/songList/detail")
    public Object commentOfSongList(@RequestParam Integer songListId){
        Map<String,Object> map=new HashMap<>();
        System.out.println(songListId);
        map.put("songListId",songListId);
        List<Comment> commentList =commentService.getAllComment(map);
        System.out.println(commentList);
        return new SuccessMessage<>(null,commentList).getMessage();

    }
    @RequestMapping(value = "/comment/song/detail")
    public Object commentOfSong(@RequestParam Integer songListId){
        Map<String,Object> map=new HashMap<>();
        map.put("songId",songListId);
        List<Comment> commentList =commentService.getAllComment(map);
        System.out.println("查询结果为：");
        System.out.println(commentList);
        return new SuccessMessage<>(null,commentList).getMessage();

    }


}
