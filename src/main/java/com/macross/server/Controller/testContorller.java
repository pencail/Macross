package com.macross.server.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.macross.server.Properties.properties;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class testContorller {

    @Autowired
    private properties properties;

    @RequestMapping ("test")
//    @ResponseBody
    public String test() {
        String a = String.valueOf(properties.getWorkDir());
        System.out.println(a);
        return a;
    }
}
