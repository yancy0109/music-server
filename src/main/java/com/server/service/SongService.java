package com.server.service;

import com.server.pojo.Song;

import java.util.List;

public interface SongService {

    List<Song> getAllSong();

    List<Song> getSongBySingerId(int id);

}
