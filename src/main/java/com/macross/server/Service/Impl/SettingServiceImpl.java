package com.macross.server.Service.Impl;

import com.macross.server.Dao.SettingMapper;
import com.macross.server.Entity.Setting;
import com.macross.server.Service.SettingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("SettingService")
public class SettingServiceImpl implements SettingService {

    @Qualifier("settingMapper")
    @Autowired
    private SettingMapper settingMapper;

    @Override
    public List<Setting> selectSetting() {
        return null;
    }
}
