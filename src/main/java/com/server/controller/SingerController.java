package com.server.controller;


import com.server.common.ErrorMessage;
import com.server.common.FatalMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.Singer;
import com.server.service.Impl.SingerServiceImpl;
import com.server.service.SingerService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    public Object addSinger(Singer singer){
        boolean flag = singerService.addSinger(singer);
        if (flag){
            return new SuccessMessage<ObjectUtils.Null>("添加成功").getMessage();
        }else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }


    //更新歌手信息
    @RequestMapping(value = "/singer/update",method = RequestMethod.POST)
    public Object updateSinger(Singer singer){
        boolean flag = singerService.updateSinger(singer);
        if (flag){
            return new SuccessMessage("更新成功").getMessage();
        }else {
            return new ErrorMessage("更新失败").getMessage();
        }
    }
    //删除鸽手信息
    @RequestMapping(value = "/singer/delete",method = RequestMethod.POST)
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
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img"
                + System.getProperty("file.separator") + "singerPic";
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
