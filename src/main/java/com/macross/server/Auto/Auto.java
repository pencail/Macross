package com.macross.server.Auto;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class Auto implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("现在时间：" + sdf.format(date));
    }
}
