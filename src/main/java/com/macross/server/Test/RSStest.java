package com.macross.server.Test;

import com.rometools.rome.feed.synd.SyndEnclosure;
import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.FeedException;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RSStest {
    public static void main(String[] args) throws MalformedURLException {
        try {
            String url = "https://mikan.ellye.org/RSS/Search?searchstr=%E7%99%BD%E6%81%8B%E5%8A%A8%E6%BC%AB%E8%90%9D%E5%8D%9C%E9%83%A8+%E6%9C%BA%E5%8A%A8%E6%88%98%E5%A3%AB%E9%AB%98%E8%BE%BE+%E6%B0%B4%E6%98%9F%E7%9A%84%E9%AD%94%E5%A5%B3";
            try (XmlReader reader = new XmlReader(new URL(url))) {

//                System.out.println(url);

                SyndFeed feed = new SyndFeedInput().build(reader);
                System.out.println(feed.getTitle());
                System.out.println("****************************");

//                System.out.println(feed);

                List<SyndEntry> entry = feed.getEntries();
//                System.out.println(entry);
                System.out.println(entry.get(0).getTitle());
                System.out.println(entry.get(0).getLink());
//                System.out.println(entry.get(0).getEnclosures().get(0).getUrl());

//                List<SyndEnclosure> losure = entry.get(0).getEnclosures();
//                System.out.println(losure);

//              entry.geturi        entry.get(entry.index).getEnclosures().getUrl()   没有颁发就用jsp


//                for (SyndEntry entry : feed.getEntries()) {
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

//                System.out.println("Done");
//                System.out.println(feed.getEntries().toArray().length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
