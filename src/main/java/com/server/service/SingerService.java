package com.server.service;

import com.server.pojo.Singer;
import com.server.pojo.Song;

import java.util.List;

/**
 * @author bai
 * @version 1.0
 */
public interface SingerService {
    List<Singer> allSinger();

    boolean addSinger (Singer singer);

    boolean updateSinger(Singer singer);

    boolean deleteSingerById(int id);



}
