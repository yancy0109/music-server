package com.server.dao;

import com.server.pojo.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {

    Admin selectAdmin(@Param("username") String username);
}
