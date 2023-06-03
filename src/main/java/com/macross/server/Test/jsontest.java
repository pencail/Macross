package com.macross.server.Test;

import com.alibaba.fastjson2.JSON;
import com.macross.server.Entity.Setting;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class jsontest {

    public static void main(String[] args) throws IOException {
//        File file = new File("/home/pencail/project/Macross/src/config/setting.json");
//        String file1 = JSON.toJSONString(file);
//        System.out.println(file1);

//        Setting setting = new Setting();
//        setting.setSetName("1");
//        setting.setSetName("2");
//        System.out.println(setting.getSetName());

        String D = "/home/pencail/Downloads/test";
        File file = new File(D);
//        System.out.println(file.mkdir());
//        System.out.println(file.exists());
//        System.out.println(!file.mkdir());

//        Path path = Paths.get(D);
//        Path path1 = Files.createDirectories(path);
//        System.out.println(path1);


//        String a = "1";
//        String b = "1";
//        String c = "2";
//        System.out.println(a.equals(b));
//        System.out.println(a.equals(c));
//        System.out.println(!(a.equals(c)));
    }

}
