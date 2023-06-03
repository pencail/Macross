package com.macross.server.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class test {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //测试springboot是否启动成功
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    @ResponseBody
    public String hello() {
        return "hello";
    }

    //测试数据库是否启动成功
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getUsers() {
        String sql = "select * from admin";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    @RequestMapping("/testwel")
    public static String wel() {
        return "admin/admin-info";
    }
}
