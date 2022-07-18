package com.server.controller;


import com.server.common.ErrorMessage;
import com.server.common.FatalMessage;
import com.server.common.SuccessMessage;
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
    public Object updateSongPic(@RequestParam("file") MultipartFile urlFile, @RequestParam("id") int id) {
        String fileName = System.currentTimeMillis() + urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "img" + System.getProperty("file.separator") + "songPic";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            boolean mkdir = file1.mkdir();
            if (mkdir){
                System.out.println("创建失败");
            }
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/img/songPic/" + fileName;
        try {
            urlFile.transferTo(dest);
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
        String fileName = urlFile.getOriginalFilename();
        String filePath = System.getProperty("user.dir") + System.getProperty("file.separator") + "song";
        File file1 = new File(filePath);
        if (!file1.exists()) {
            boolean mkdir = file1.mkdir();
            if (mkdir){
                System.out.println("创建失败");
            }
        }

        File dest = new File(filePath + System.getProperty("file.separator") + fileName);
        String storeUrlPath = "/song/" + fileName;
        try {
            urlFile.transferTo(dest);
            Song song = new Song();
            song.setId(id);
            song.setUrl(storeUrlPath);
            int res = songService.updateSong(song);
            if (res > 0) {
                return new SuccessMessage<>("更新成功", storeUrlPath).getMessage();
            } else {
                return new ErrorMessage("更新失败").getMessage();
            }
        } catch (IOException e) {
            return new FatalMessage("更新失败" + e.getMessage()).getMessage();
        }
    }
    @RequestMapping("/song/delete")
    public  Object deleteSong(@RequestParam Integer id){
       int res= songService.deleteSong(id);
       if (res>0) return  new SuccessMessage<>("删除成功！",null).getMessage();
       else return new ErrorMessage("删除失败！").getMessage();
    }

}
