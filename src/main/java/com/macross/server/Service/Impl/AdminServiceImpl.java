package com.macross.server.Service.Impl;

import com.macross.server.Entity.Admin;
import com.macross.server.Dao.AdminMapper;

import com.macross.server.Service.AdminService;
import com.macross.server.Utils.MD5Util;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;

@Service("AdminService")
public class AdminServiceImpl implements AdminService {

    @Qualifier("adminMapper")
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     *
     * @return
     */
    @Override
    public Admin login(String username, String password) {
        Admin admin = adminMapper.login(username, password);
        return admin;
    }

    /**
     * 更新管理员信息
     *
     * @return
     */
    @Override
    public String updateAdmin(Admin admin, HttpServletRequest request) {
        String UpdataFlag = "";
        //获取session中的admin信息
        Admin sessionAdmin = (Admin) request.getSession().getAttribute("admin");
        /*System.out.println(sessionAdmin.getAdminName()+" "+sessionAdmin.getAdminPassword());*/

        /* 获取要修改密码的用户的id */
        admin.setAdminId(sessionAdmin.getAdminId());
        /* 对新密码进行加密 */
        admin.setAdminPassword(MD5Util.MD5Utils(admin.getAdminPassword()));
        /*System.out.println(admin.getAdminPassword());*/

        int n = adminMapper.updateAdmin(admin);


        if (n > 0) {
            Admin newAdmin = adminMapper.selectAdmin(admin.getAdminId());
            request.getSession().setAttribute("admin", newAdmin);

            UpdataFlag = "true";

            return UpdataFlag;
        }

        UpdataFlag = "false";

        return UpdataFlag;
    }

    /**
     * 通过id查找管理员
     *
     * @return
     */
    @Override
    public Admin selectByid(int adminId) {
        Admin adminid = adminMapper.selectAdmin(adminId);
        return adminid;
    }


}
