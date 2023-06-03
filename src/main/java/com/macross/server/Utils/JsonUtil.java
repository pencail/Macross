package com.macross.server.Utils;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.macross.server.Entity.Admin;

import jakarta.annotation.Resource;

public class JsonUtil {

    /**
     * json格式化
     *
     * @param str
     * @return
     */
    public static String JsonFormat(String str) {

//        String json = "{\"code\": 0,\"data\": ["+str+"]}";
        String json = "{\"code\": 0,\"data\": " + str + "}";
        JSONObject j = JSONObject.parseObject(json);
        String o = JSON.toJSONString(j);

        return o;

    }

    public static void main(String[] args) {

    }
}
