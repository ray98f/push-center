package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.req.AppListReqDTO;
import com.zte.msg.pushcenter.pccore.entity.App;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface AppMapper extends BaseMapper<App> {

    List<App> listApp(AppListReqDTO appListReqDTO);

    List<App> listAllApp();

    int deleteApp(List<Integer> appIds, String userName);

    int updateApp(App app);

    int insertApp(App app);

    Integer selectAppId(String appName);

    int resetKey(String appSecret, String userName, Integer appId);

    @Select("SELECT app_name FROM app WHERE id = #{appId}")
    String selectAppName(@Param("appId") Long appId);
}
