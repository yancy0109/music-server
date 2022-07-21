package com.server.controller;


import com.server.common.ErrorMessage;
import com.server.common.FatalMessage;
import com.server.common.SuccessMessage;
import com.server.constant.Constants;
import com.server.pojo.Song;
import com.server.service.SongService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class SongController {

    @Resource(name = "songServiceImpl")
    SongService songService;
    @RequestMapping("/song")
    public Object getAllSong() {

        return new SuccessMessage<>("查询成功", songService.getSongByIf(null)).getMessage();
    }

    @RequestMapping("song/singer/detail")
    public Object getSongBySingerId(@RequestParam("singerId") int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("singerId", id);
        return new SuccessMessage<>("查询成功", songService.getSongByIf(map)).getMessage();
    }

    @RequestMapping("song/detail")
    public Object getSongOfId(@RequestParam Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        return new SuccessMessage<>("查询成功！", songService.getSongByIf(map)).getMessage();
    }

    @RequestMapping("/song/singerName/detail")
    public Object getSongOfSongName(@RequestParam String name) {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name.trim());
        return new SuccessMessage<>(null, songService.getSongByIf(map)).getMessage();
    }

    //更新歌曲图片
    @ResponseBody
    @RequestMapping(value = "/song/img/update", method = RequestMethod.POST)
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id ) {
        String uuid= UUID.randomUUID().toString().replace("-","");
        String houzhui=null;
        if(urlFile.getOriginalFilename()!=null)
        houzhui=urlFile.getOriginalFilename().substring(urlFile.getOriginalFilename().lastIndexOf("."));
        String fileName=uuid+houzhui;
        System.out.println(urlFile.getOriginalFilename());
        System.out.println(fileName);
        String filePath= Constants.FILE_LACATION+"\\img\\songPic";
        System.out.println(filePath);
        try {
            urlFile.transferTo(new File(filePath+"\\"+fileName));
            String storeUrlPath = "/img/songPic/" + fileName;
            Song song = new Song();
            song.setId(id);
            song.setPic(storeUrlPath);
            int res = songService.updateSong(song);
            if (res > 0) {
                return new SuccessMessage<>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }


    }

    // 更新歌曲信息
    @ResponseBody
    @RequestMapping(value = "/song/update", method = RequestMethod.POST)
    public Object updateSongMsg(HttpServletRequest req) {
        String id = req.getParameter("id").trim();
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String lyric = req.getParameter("lyric").trim();

        Song song = new Song();
        song.setId(Integer.parseInt(id));
        song.setSingerId(Integer.parseInt(singer_id));
        song.setName(name);
        song.setIntroduction(introduction);
        song.setUpdateTime(new Date());
        song.setLyric(lyric);

        int res = songService.updateSong(song);
        if (res > 0) {
            return new SuccessMessage<ObjectUtils.Null>("修改成功").getMessage();
        } else {
            return new ErrorMessage("修改失败").getMessage();
        }
    }

    // 更新歌曲
    @ResponseBody
    @RequestMapping(value = "/song/url/update", method = RequestMethod.POST)
    public Object updateSongUrl(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {

        String fileName=System.currentTimeMillis()+urlFile.getOriginalFilename();
        System.out.println(fileName);
        String filePath=Constants.SONG_PATH;
        System.out.println(filePath);
        try {
            urlFile.transferTo(new File(filePath+"\\"+fileName));
            String storeUrlPath = "/song/" + fileName;
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            int res = songService.updateSong(song);
            if (res > 0) {
                return new SuccessMessage<>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }


    }
    @RequestMapping("/song/delete")
    public  Object deleteSong(@RequestParam Integer id){
       int res= songService.deleteSong(id);
       if (res>0) return  new SuccessMessage<>("删除成功！",null).getMessage();
       else return new ErrorMessage("删除失败！").getMessage();
    }

    // 添加歌曲
    @ResponseBody
    @RequestMapping(value = "/song/add", method = RequestMethod.POST)
    public Object addSong(HttpServletRequest req, @RequestParam("file") MultipartFile mpfile) {
        String singer_id = req.getParameter("singerId").trim();
        String name = req.getParameter("name").trim();
        String introduction = req.getParameter("introduction").trim();
        String pic = "/img/songPic/tubiao.jpg";
        String lyric = req.getParameter("lyric").trim();

        String fileName = System.currentTimeMillis()+mpfile.getOriginalFilename();
        String filePath = Constants.SONG_PATH;
        File file1 = new File(filePath);
        if (!file1.exists()) {
            Boolean result=file1.mkdir();
            if(!result) System.out.println("创建文件失败！");
        }

        File dest = new File(filePath +"\\" + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            mpfile.transferTo(dest);
            Song song = new Song();
            song.setSingerId(Integer.parseInt(singer_id));
            song.setName(name);
            song.setIntroduction(introduction);
            song.setCreateTime(new Date());
            song.setUpdateTime(new Date());
            song.setPic(pic);
            song.setLyric(lyric);
            song.setUrl(storeUrlPath);
            int res = songService.addSong(song);
            if (res>0) {
                return new SuccessMessage<>("上传成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("上传失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("上传失败" + e.getMessage()).getMessage();
        }
    }


}
