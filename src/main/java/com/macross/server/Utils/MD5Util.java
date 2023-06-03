package com.macross.server.Utils;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


    public static String MD5Utils(String str) {
        //生成md5加密摘要
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        //计算md5函数
        md5.update(str.getBytes());

        byte[] byteDigest = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");
        for (int offset = 0; offset < byteDigest.length; offset++) {
            i = byteDigest[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        //32位加密
        return buf.toString();
        // 16位的加密
        //return buf.toString().substring(8, 24);

    }

    //使用一次加密，二次解密
    public static String converMD5(String str) {
        char[] a = str.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static void main(String args[]) {
        String md = "123456";
        String md5 = MD5Utils(md);
        System.out.println("md5加密：" + md5);
        System.out.println("对md5值进行加密：" + converMD5(md5));
        System.out.println("对加密的md5值进行解密：" + converMD5(converMD5(md5)));
    }
}