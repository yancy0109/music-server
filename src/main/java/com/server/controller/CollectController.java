package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.Collect;
import com.server.service.CollectService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

public class CollectController {
    @Resource(name ="collectServiceImpl")
    private CollectService collectService;
    //根据用户id和歌曲id删除用户对应的收藏
    @RequestMapping(value = "/collection/delete",method = RequestMethod.DELETE)
    public Object deleteCollection (HttpServletRequest request){
        Integer userId=Integer.parseInt(request.getParameter("userId")) ;
        Integer songId=Integer.parseInt(request.getParameter("songId"));
        int res=collectService.deleteCollect(userId,songId);
        if(res>0)
        return  new SuccessMessage<>("取消收藏",false).getMessage();
        else return  new ErrorMessage("取消收藏失败！").getMessage();
    }
    //删除后，根据id查找出该用户对应的收藏列表
    @RequestMapping(value = "/collection/detail", method = RequestMethod.GET)
    public Object collectionOfUser(HttpServletRequest request) {
        Integer userId=Integer.parseInt(request.getParameter("userId"));
        List<Collect> collectList=collectService.collectionOfUser(userId);
        return  new SuccessMessage<>("取消收藏！",collectList).getMessage();
    }
    @RequestMapping(value = "/collection/status",method = RequestMethod.POST)
    public Object isCollection(Collect collect){
        System.out.println(collect);
         Boolean res=collectService.isCollection(collect);
       return new SuccessMessage<>(null,res).getMessage();

    }
    @RequestMapping(value = "/collection/add")
    public Object setCollection(Collect collect){
     collect.setCreateTime(new Date());
     int res=collectService.setCollection(collect);
      if(res>0) return new SuccessMessage<>("收藏成功！",true).getMessage();
      else return new SuccessMessage<>("收藏失败！",false).getMessage();
    }



}
