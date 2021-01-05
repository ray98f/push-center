package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.entity.App;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppMapper;
import com.zte.msg.pushcenter.pccore.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:19
 */
@Service
@Slf4j
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public List<App> listApp() {
        List<App> appList = appMapper.listApp();
        if (null != appList && !appList.isEmpty()) {
            log.info("服务列表获取成功");
            return appList;
        } else {
            log.error("服务列表获取失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public void deleteApp(Integer appId) {
        if (Objects.isNull(appId)) {
            log.error("服务ID返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appMapper.deleteApp(appId);
        if (result > 0) {
            log.info("服务删除成功");
        } else {
            log.error("服务删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void updateApp(App app) {
        if (Objects.isNull(app)) {
            log.error("服务信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appMapper.updateApp(app);
        if (result > 0) {
            log.info("服务修改成功");
        } else {
            log.error("服务修改失败");
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void insertApp(String appName) {
        if (StringUtils.isBlank(appName)) {
            log.error("服务信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer id = appMapper.selectAppId(appName);
        if (Objects.nonNull(id)) {
            log.error("服务已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        int result = appMapper.insertApp(appName);
        if (result > 0) {
            log.info("服务权限成功");
        } else {
            log.error("服务权限失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }
}