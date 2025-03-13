package com.macross.server.Utils;

import java.lang.Exception;
import java.net.URLEncoder;

public class urlEncodeChineseUtil {

    public static String urlEncode(String str) throws Exception {
        String resultUrl = "";

        //遍历字符串
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            //只对汉字进行处理
            if (isChineseChar(charAt)) {
                String encode = URLEncoder.encode(charAt+"", "UTF-8");
                resultUrl+=encode;
            }else {
                resultUrl+=charAt;
            }
        }
        //resultUrl就是处理过的汉字
        return resultUrl;
    }

    /**
     * 判断汉字的方法，只要编码在\u4e00到\u9fa5之间的都是汉字
     * @param c
     * @return
     */
    public static boolean isChineseChar(char c) {
        return String.valueOf(c).matches("[\u4e00-\u9fa5]");
    }

    /**
     * 修改不符合FileUtils.copyURLToFile规范的特殊符号
     * @param str
     * @return
     * @throws Exception
     */
    public static String symbolencoder(String str) throws Exception {
        str = str.replace("/", "-");
        str = str.replace(":", "：");

        return str;
    }

    public static void main(String[] args) throws Exception {
        String url = "https://mikanime.tv/RSS/Search?searchstr=刀剑神域";
        System.out.println(urlEncodeChineseUtil.urlEncode(url));
    }
}
