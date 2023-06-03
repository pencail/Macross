package com.macross.server.Service;

import com.macross.server.Entity.Admin;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.ibatis.annotations.Param;

import java.net.http.HttpRequest;

public interface AdminService {

    //登录
    Admin login(@Param("username") String username, @Param("password") String password);

    //更改信息
    String updateAdmin(Admin adminname, HttpServletRequest request);

    //
    Admin selectByid(int adminId);

}
