package com.macross.server.Dao;


import com.macross.server.Entity.Admin;
import com.macross.server.Entity.Setting;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "settingMapper")
public interface SettingMapper {
    List<Setting> selectSettingByName(String setName);

    int updateSetting(Setting setting);
}
