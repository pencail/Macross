package com.macross.server.Service.Impl;

import com.macross.server.Properties.properties;
import com.macross.server.Dao.RssMapper;
import com.macross.server.Dao.SettingMapper;
import com.macross.server.Entity.RSS;
import com.macross.server.Entity.Setting;
import com.macross.server.Service.RssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service("RssService")
public class RssServiceImpl implements RssService {

    @Autowired
    private properties properties;

    @Qualifier("rssMapper")
    @Autowired
    private RssMapper rssMapper;

    @Qualifier("settingMapper")
    @Autowired
    private SettingMapper settingMapper;

    @Override
    public RSS selectById(int rssId) {
        RSS rssid = rssMapper.selectRss(rssId);
        return rssid;
    }

    @Override
    public List<RSS> selectRssByUrl(String rssLink) {
        return rssMapper.selectRssByUrl(rssLink);
    }

    @Override
    public List<RSS> getRSS() {
        return rssMapper.selectRssAll();
    }

    @Override
    public boolean insertRss(RSS rss) {
        int n = rssMapper.insertRss(rss);
        if (n > 0) {
            return true;
        }
        return false;
    }

    @Override
    public int deleteById(int rssId) {
        return rssMapper.deleteRss(rssId);
    }

    @Override
    public String updateSetting(String Dir, String auto) {
        Setting setting = new Setting();
        int n = 0;

        List<Setting> Dirsql = settingMapper.selectSettingByName("RSSdownloadDir");
        List<Setting> autodown = settingMapper.selectSettingByName("AutoDownload");

        if (auto.equals(autodown.get(0).getSetValue()) && Dir.equals(Dirsql.get(0).getSetValue())) {
            return "fal";
        }

        //如果向开启路径验证请取消这里和下边 “file.exists() &&” 的注释
        /*File file = new File(Dir);*/

        //判断 路径是否存在
        if (/*file.exists() &&*/ !Dir.equals(Dirsql.get(0).getSetValue())) {
            setting.setSetName("RSSdownloadDir");
            setting.setSetValue(Dir);
            n = settingMapper.updateSetting(setting);
            if (n == 0) {
                return "fail";
            }
        }


        setting.setSetName("AutoDownload");
        setting.setSetValue(auto);
        n = settingMapper.updateSetting(setting);
        if (n > 0) {
            return "true";
        }

        return "false";
    }

    @Override
    public String download() {
//        String setName = "RSSdownloadDir";
//        List<Setting> download = settingMapper.selectSettingByName(setName);
//
//        return download.get(0).getSetValue();
        String dir = properties.getWorkDir()+File.separator+"torrent";
        return dir;
    }

    @Override
    public String autoDownload() {
        String setName = "AutoDownload";
        List<Setting> autoDowmload = settingMapper.selectSettingByName(setName);
        return autoDowmload.get(0).getSetValue();
    }

}
