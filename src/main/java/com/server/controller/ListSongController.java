package com.server.controller;

import com.server.common.ErrorMessage;
import com.server.common.SuccessMessage;
import com.server.pojo.ListSong;
import com.server.service.ListSongService;
import org.apache.commons.lang3.ObjectUtils.Null;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
public class ListSongController {

    @Resource(name="listSongServiceImpl")
    private ListSongService listSongService;

    // 给歌单添加歌曲 manager
    @RequestMapping(value = "/listSong/add", method = RequestMethod.POST)
    public Object addListSong(HttpServletRequest req) {
        String song_id = req.getParameter("songId").trim();
        String song_list_id = req.getParameter("songListId").trim();

        ListSong listsong = new ListSong();
        listsong.setSongId(Integer.parseInt(song_id));
        listsong.setSongListId(Integer.parseInt(song_list_id));

        boolean res = listSongService.addListSong(listsong);
        if (res) {
            return new SuccessMessage<Null>("添加成功").getMessage();
        } else {
            return new ErrorMessage("添加失败").getMessage();
        }
    }

    // 删除歌单里的歌曲 manager
    @RequestMapping(value = "/listSong/delete", method = RequestMethod.GET)
    public Object deleteListSong(HttpServletRequest req) {
        String songId = req.getParameter("songId");

        boolean res = listSongService.deleteListSong(Integer.parseInt(songId));
        if (res) {
            return new SuccessMessage<Null>("删除成功").getMessage();
        } else {
            return new ErrorMessage("删除失败").getMessage();
        }
    }

    // 返回歌单里指定歌单 ID 的歌曲 manager
    @RequestMapping(value = "/listSong/detail", method = RequestMethod.GET)
    public Object listSongOfSongId(HttpServletRequest req) {
        String songListId = req.getParameter("songListId");

        return new SuccessMessage<>("添加成功", listSongService.listSongOfSongId(Integer.parseInt(songListId)))
                .getMessage();
    }

}
