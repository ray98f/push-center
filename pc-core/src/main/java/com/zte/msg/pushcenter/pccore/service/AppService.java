package com.zte.msg.pushcenter.pccore.service;


import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.AppListReqDTO;
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

    PageInfo<App> listApp(AppListReqDTO appListReqDTO);

    void deleteApp(List<Integer> appIds, String userName);

    void updateApp(App app);

    void insertApp(App app);

    void resetKey(String userName, Integer appId);

}
