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
        //对链接中的中文进行编码
        RssLink = urlEncodeChineseUtil.urlEncode(RssLink);

        SyndFeed feed = null;
        XmlReader reader = new XmlReader(new URL(RssLink));

        feed = new SyndFeedInput().build(reader);

        return feed;

    }

    public static void main(String[] args) throws Exception {
        String url = "https://mikanime.tv/RSS/Search?searchstr=刀剑神域";
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
