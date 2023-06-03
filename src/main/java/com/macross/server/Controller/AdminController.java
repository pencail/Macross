package com.macross.server.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.macross.server.Entity.Admin;
import com.macross.server.Service.AdminService;
import com.macross.server.Utils.MD5Util;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

@Controller
public class AdminController {

    @Resource
    private AdminService adminService;

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String adminlogin(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             HttpServletRequest request) {

        //MD5加密
        String md5pwd = MD5Util.MD5Utils(password);

        Admin admin = adminService.login(username, md5pwd);


        String flag = "";

        if (admin != null) {
//            request.getSession().setAttribute("flag", 0);
//
//            request.getSession().setAttribute("adminname", admin.getAdminName());
            request.getSession().setAttribute("admin", admin);
//
//            return "1/index";
            flag = "true";
            return flag;
        }

//        request.getSession().setAttribute("flag", 1);
//        return "index";
        flag = "false";
        return flag;
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session,
                         SessionStatus sessionStatus) {
        session.invalidate();
        sessionStatus.setComplete();
        return "/index";
    }

    @RequestMapping("/index")
    public String AdminIndex(HttpServletRequest request) {
        Admin session_admin = (Admin) request.getSession().getAttribute("admin");

        /*        判断是否有登录session,没有则不允许进入后台*/
        if (session_admin != null) {
            return "admin/index";
        } else {
            return "admin/404";
        }

    }

    /**
     * 更新管理员信息
     *
     * @param admin
     * @param request
     * @return
     */
    @PostMapping("/updateAdmin")
    @ResponseBody
    public String updateAdmin(Admin admin, HttpServletRequest request) {
/*        System.out.println(admin.getAdminName());
        System.out.println(admin.getAdminPassword());*/
        //无法获取到前端传过来的password数据
        return adminService.updateAdmin(admin, request);
        /*return true;*/
    }

    /**
     * 进入欢迎页面
     *
     * @return
     */
    @RequestMapping("welcome")
    public String welcome() {
        return "system/welcome";
    }

    /**
     * 进入管理员信息页面
     *
     * @return
     */
    @RequestMapping("/info")
    public String info(Model model,
                       HttpServletRequest request) throws JsonProcessingException {
        Admin session_admin = (Admin) request.getSession().getAttribute("admin");
        Admin admin = adminService.selectByid(session_admin.getAdminId());

        model.addAttribute("info_adminId", admin.getAdminId());
        model.addAttribute("info_name", admin.getAdminName());

        return "admin/info";
    }

    /**
     * 进入更新页面
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/update")
    public String update(Model model,
                         HttpServletRequest request) {
        Admin session_admin = (Admin) request.getSession().getAttribute("admin");
        Admin admin = adminService.selectByid(session_admin.getAdminId());

        model.addAttribute("admin_name", admin.getAdminName());

        return "admin/update";
    }

}
