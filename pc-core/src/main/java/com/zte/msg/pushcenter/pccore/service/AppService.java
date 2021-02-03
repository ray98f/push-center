package com.zte.msg.pushcenter.pccore.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.AppListReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WhiteIpResDTO;
import com.zte.msg.pushcenter.pccore.entity.App;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/31 8:40
 */
public interface AppService {

    /**
     * 获取所有应用列表
     * @return
     */
    List<App> listAllApp();

    /**
     * 获取应用列表
     * @param appListReqDTO
     * @return
     */
    Page<App> listApp(AppListReqDTO appListReqDTO);

    /**
     * 删除应用
     * @param appIds
     * @param userName
     */
    void deleteApp(List<Integer> appIds, String userName);

    /**
     * 修改应用
     * @param app
     */
    void updateApp(App app);

    /**
     * 新增应用
     * @param app
     */
    void insertApp(App app);

    /**
     * 重置密钥
     * @param userName
     * @param appId
     */
    void resetKey(String userName, Integer appId);

    /**
     * 获取应用名
     * @param appId
     * @return
     */
    String getAppName(Long appId);

    /**
     * 获取白名单ip列表
     * @return
     */
    List<WhiteIpResDTO> listWhiteIp();

    /**
     * 获取白名单ip
     * @param appId
     * @return
     */
    List<String> selectWhiteIp(Long appId);
}
