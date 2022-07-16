package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.Comment;
import com.server.service.CommentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Resource(name="commentServiceImpl")
    private CommentService commentService;
    @RequestMapping(value = "/comment/song/detail",method = RequestMethod.GET)
   public Object getCommentOfSongId( HttpServletRequest request){
          Integer songId=Integer.parseInt(request.getParameter("songId"));
          List<Comment> commentList= commentService.commentOfSongId(songId);
          return new SuccessMessage<List<Comment>>(null,commentList).getMessage();
    }
    @RequestMapping(value = "/comment/delete",method = RequestMethod.GET)
    public Object deleteComment (HttpServletRequest request){
        Integer id=Integer.parseInt(request.getParameter("id"));
        int res=commentService.deleteComment(id);
        if(res>0) return new SuccessMessage<Boolean>("删除成功！",false).getMessage();
        else return  new ErrorMessage("删除失败！").getMessage();

    }
}
