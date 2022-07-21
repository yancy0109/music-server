package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.FatalMessage;
import com.server.common.SuccessMessage;
import com.server.constant.Constants;
import com.server.pojo.SongList;
import com.server.service.SongListService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
public class SongListController {

    @Resource(name="songListServiceImpl")
    private SongListService songListService;


    // 添加歌单 manager
    @RequestMapping(value = "/songList/add", method = RequestMethod.POST)
    public Object addSongList(HttpServletRequest req) {
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();
        String pic = "/img/songListPic/123.jpg";

        SongList songList = new SongList();
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);
        songList.setPic(pic);

        boolean res = songListService.addSongList(songList);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单 manager
    @RequestMapping(value = "/songList/delete", method = RequestMethod.GET)
    public Object deleteSongList(HttpServletRequest req) {
        String id = req.getParameter("id");

        boolean res = songListService.deleteSongList(Integer.parseInt(id));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回所有歌单 manager
    @RequestMapping(value = "/songList", method = RequestMethod.GET)
    public Object allSongList() {
        return new SuccessMessage<>(null, songListService.allSongList()).getMessage();
    }

    // 更新歌单信息 manager
    @RequestMapping(value = "/songList/update", method = RequestMethod.POST)
    public Object updateSongListMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String title = req.getParameter("title").trim();
        String introduction = req.getParameter("introduction").trim();
        String style = req.getParameter("style").trim();

        SongList songList = new SongList();
        songList.setId(Integer.parseInt(id));
        songList.setTitle(title);
        songList.setIntroduction(introduction);
        songList.setStyle(style);

        boolean res = songListService.updateSongListMsg(songList);
        if (res) {
            return new SuccessMessage<Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌单图片 manager
    @RequestMapping(value = "/songList/img/update", method = RequestMethod.POST)
    public Object updateSongListPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id ) {
        String uuid= UUID.randomUUID().toString().replace("-","");
        String suffix=null;
        if(urlFile.getOriginalFilename()!=null)
            suffix=urlFile.getOriginalFilename().substring(urlFile.getOriginalFilename().lastIndexOf("."));
        String fileName=uuid+suffix;
        System.out.println(urlFile.getOriginalFilename());
        System.out.println(fileName);
        String filePath= Constants.FILE_LACATION+"\\img\\songListPic";
        System.out.println(filePath);
        try {
            urlFile.transferTo(new File(filePath+"\\"+fileName));
            String storeUrlPath = "/img/songListPic/" + fileName;
            SongList songList = new SongList();
            songList.setId(id);
            songList.setPic(storeUrlPath);
            boolean res = songListService.updateSongListImg(songList);
            if (res) {
                return new SuccessMessage<>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }
}
