package com.macross.server.Service;

import com.macross.server.Entity.Setting;

import java.util.List;

public interface SettingService {
    List<Setting> selectSetting();
}
