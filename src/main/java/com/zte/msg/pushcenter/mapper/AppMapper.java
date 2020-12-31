package com.zte.msg.pushcenter.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.entity.App;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AppMapper extends BaseMapper<App> {

    List<App> listApp();

    int deleteApp(Integer appId);

    int updateApp(App app);

    int insertApp(String appName);

    Integer selectAppId(String appName);
}
