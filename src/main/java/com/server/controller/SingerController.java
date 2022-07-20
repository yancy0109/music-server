package com.server.controller;


import com.server.common.ErrorMessage;
import com.server.common.FatalMessage;
import com.server.common.SuccessMessage;
import com.server.constant.Constants;
import com.server.pojo.Singer;
import com.server.service.Impl.SingerServiceImpl;
import com.server.service.SingerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class SingerController {

    @Autowired
    SingerService singerService;


    //返回所有歌手
    @RequestMapping(value = "/singer",method = RequestMethod.GET)
    public Object allSinger(){
        return new SuccessMessage<>(null,singerService.allSinger()).getMessage();
    }
    //添加歌手
    @RequestMapping(value = "/singer/add",method = RequestMethod.POST)
    public Object addSinger(HttpServletRequest req){
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/avatorImages/user.jpg";

        Singer singer = new Singer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(myBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        singer.setPic(pic);
        boolean flag = singerService.addSinger(singer);
        if (flag){
            return new SuccessMessage<ObjectUtils.Null>("添加成功").getMessage();
        }else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }


    //更新歌手信息
    @RequestMapping(value = "/singer/update",method = RequestMethod.POST)
    public Object updateSinger(HttpServletRequest req){
        String id = req.getParameter("id").trim();
        String name = req.getParameter("name").trim();
        String sex = req.getParameter("sex").trim();
        String birth = req.getParameter("birth").trim();
        String location = req.getParameter("location").trim();
        String introduction = req.getParameter("introduction").trim();

        Singer singer = new Singer();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myBirth = new Date();
        try {
            myBirth = dateFormat.parse(birth);
        } catch (Exception e) {
            e.printStackTrace();
        }
        singer.setId(Integer.parseInt(id));
        singer.setName(name);
        singer.setSex(new Byte(sex));
        singer.setBirth(myBirth);
        singer.setLocation(location);
        singer.setIntroduction(introduction);
        boolean flag = singerService.updateSinger(singer);
        if (flag){
            return new SuccessMessage("更新成功").getMessage();
        }else {
            return new ErrorMessage("更新失败").getMessage();
        }
    }
    //删除鸽手信息
    @RequestMapping(value = "/singer/delete",method = RequestMethod.GET)
    public Object deleteSinger(@RequestParam("id") int id){
        boolean flag = singerService.deleteSingerById(id);
        if (flag){
            return new SuccessMessage("删除成功").getMessage();
        }else {
            return new ErrorMessage("删除失败").getMessage();
        }

    }

    //更新鸽手头像
    @RequestMapping(value = "/singer/avatar/update",method = RequestMethod.POST)
    public Object updateSingerPic(@RequestParam("file")MultipartFile avatorFile,@RequestParam("id") int id){
        String fileName = System.currentTimeMillis() + avatorFile.getOriginalFilename();
//        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
//                + System.getProperty("file.separator") + "singerPic";
        String filePath= Constants.FILE_LACATION+"\\img\\singerPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            file1.mkdir();
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String imgPath = "/img/singerPic/" + fileName;
        try {
            avatorFile.transferTo(dest);
            Singer singer = new Singer();
            singer.setId(id);
            singer.setPic(imgPath);

            boolean res = singerService.updateSingerPic(singer);
            if (res) {
                return new SuccessMessage<String>("上传成功", imgPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }

}
