package com.macross.server.Service;

import com.macross.server.Entity.Admin;
import com.macross.server.Entity.RSS;
import com.macross.server.Entity.Setting;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface RssService {

    RSS selectById(int rssId);

    List<RSS> selectRssByUrl(String rssLink);

    List<RSS> getRSS();

    boolean insertRss(RSS rss);

    int deleteById(int rssId);

    String updateSetting(String Dir, String auto);


    String download();

    String autoDownload();

}
