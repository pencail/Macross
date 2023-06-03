package com.macross.server.Utils;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.lang.Exception;
import java.net.URL;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class RssUtil {

    public static SyndFeed RssSearch(String RssLink) throws Exception {

        /*判断链接中是否含有空格，有些链接在传入后台中会将+号转成空格，此判断是将空格再次转会+号*/
        if (RssLink.contains(" ")) {
            RssLink = (RssLink.replace(" ", "+"));
        }

        SyndFeed feed = null;
        XmlReader reader = new XmlReader(new URL(RssLink));

        feed = new SyndFeedInput().build(reader);

        return feed;

    }

    public static void main(String[] args) throws Exception {
        String url = "https://mikan.ellye.org/RSS/Search?searchstr=%E7%99%BD%E6%81%8B%E5%8A%A8%E6%BC%AB%E8%90%9D%E5%8D%9C%E9%83%A8+%E6%9C%BA%E5%8A%A8%E6%88%98%E5%A3%AB%E9%AB%98%E8%BE%BE+%E6%B0%B4%E6%98%9F%E7%9A%84%E9%AD%94%E5%A5%B3";
        SyndFeed feed = RssSearch(url);
//        System.out.println(feed);
        System.out.println(feed.getTitle());
        System.out.println("****************************");

        List<SyndEntry> entry = feed.getEntries();
//        System.out.println(entry.get(1).getUri());

        List<SyndEnclosure> losure = entry.get(0).getEnclosures();
//        System.out.println(losure);

//        List<SyndEntry> list = null;
//
//        for (int i = 0; i <= feed.getEntries().toArray().length; i++) {
//            list.addAll(entry.get(i));
//        }
//        System.out.println(list);

//                 for (SyndEntry entry : feed.getEntries()) {
//                    System.out.println(entry);
//                    System.out.println(entry.getEnclosures());
//
//                    List<SyndEnclosure> list = entry.getEnclosures();
//                    System.out.println(list.get(0).getUrl());
//                    System.out.println(entry);
//
//
//                    System.out.println("*****************************");
//                }

    }
}
