package com.macross.server.Dao;

import com.macross.server.Entity.RSS;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "rssMapper")
public interface RssMapper {

    RSS selectRss(Integer rssId);

    List<RSS> selectRssByUrl(String rssLink);

    List<RSS> selectRssAll();

    int insertRss(RSS rss);

    int deleteRss(int id);

}
