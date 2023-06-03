package com.macross.server.Dao;


import com.macross.server.Entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "adminMapper")
public interface AdminMapper {

    //登录
    Admin login(@Param("adminname") String adminname, @Param("password") String password);

    //更新
    int updateAdmin(Admin admin);

    //通过id查找admin
    Admin selectAdmin(Integer adminId);
}
