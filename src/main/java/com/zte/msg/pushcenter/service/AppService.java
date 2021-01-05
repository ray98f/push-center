package com.zte.msg.pushcenter.service;

import com.zte.msg.pushcenter.entity.App;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/31 8:40
 */
public interface AppService {

    List<App> listApp();

    void deleteApp(Integer appId);

    void updateApp(App app);

    void insertApp(String appName);
}
