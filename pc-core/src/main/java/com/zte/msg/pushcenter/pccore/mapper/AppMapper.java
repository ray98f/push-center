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

    /**
     * 应用列表获取
     * @param appListReqDTO
     * @return
     */
    List<App> listApp(AppListReqDTO appListReqDTO);

    /**
     * 获取所有应用
     * @return
     */
    List<App> listAllApp();

    /**
     * 删除应用
     * @param appIds
     * @param userName
     * @return
     */
    int deleteApp(List<Integer> appIds, String userName);

    /**
     * 修改应用
     * @param app
     * @return
     */
    int updateApp(App app);

    /**
     * 新增应用
     * @param app
     * @return
     */
    int insertApp(App app);

    /**
     * 获取appId
     * @param appName
     * @return
     */
    Integer selectAppId(String appName);

    /**
     * 重置密钥
     * @param appSecret
     * @param userName
     * @param appId
     * @return
     */
    int resetKey(String appSecret, String userName, Integer appId);

    /**
     * 获取应用名称
     * @param appId
     * @return
     */
    @Select("SELECT app_name FROM app WHERE id = #{appId}")
    String selectAppName(@Param("appId") Long appId);
}
