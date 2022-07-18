package com.server.service;


import com.server.pojo.Collect;

import java.util.List;

public interface CollectService {
    List<Collect> collectionOfUser(Integer userId);
    int deleteCollect( Integer userId,  Integer songId);
    Boolean isCollection(Collect collect);
    int setCollection(Collect collect);
}
