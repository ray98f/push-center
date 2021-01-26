package com.zte.msg.pushcenter.pccore.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.AppListReqDTO;
import com.zte.msg.pushcenter.pccore.entity.App;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppMapper;
import com.zte.msg.pushcenter.pccore.service.AppService;
import com.zte.msg.pushcenter.pccore.utils.Constants;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
    public List<App> listAllApp(){
        return appMapper.listAllApp();
    }

    @Override
    public Page<App> listApp(AppListReqDTO appListReqDTO) {
        PageReqDTO pageReqDTO = new PageReqDTO();
        BeanUtils.copyProperties(appListReqDTO, pageReqDTO);
        PageHelper.startPage(appListReqDTO.getPage().intValue(), appListReqDTO.getSize().intValue());
        return appMapper.listApp(pageReqDTO.of(), appListReqDTO);
    }

    @Override
    public void deleteApp(List<Integer> appIds, String userName) {
        if (null == appIds || appIds.isEmpty()) {
            log.error("服务ID返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appMapper.deleteApp(appIds, userName);
        if (result > 0) {
            log.info("服务删除成功");
        } else {
            log.error("服务删除异常");
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
    public void insertApp(App app) {
        if (Objects.isNull(app)) {
            log.error("服务信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer id = appMapper.selectAppId(app.getAppName());
        if (Objects.nonNull(id)) {
            log.error("服务已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        app.setAppKey(Constants.ZTE_NAME + TokenUtil.getTimestamp() + TokenUtil.getRandomString(5));
        app.setAppSecret(TokenUtil.getUuId() + TokenUtil.getRandomString(16));
        int result = appMapper.insertApp(app);
        if (result > 0) {
            log.info("服务权限成功");
        } else {
            log.error("服务权限失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public void resetKey(String userName, Integer appId) {
        if (StringUtils.isBlank(userName) || Objects.isNull(appId)) {
            log.error("服务Id为空");
            throw new CommonException(ErrorCode.ENUM_VALUE_ERROR);
        }
        String appSecret = TokenUtil.getUuId() + TokenUtil.getRandomString(16);
        int result = appMapper.resetKey(appSecret, userName, appId);
        if (result > 0) {
            log.info("重置密钥成功");
        } else {
            log.error("重置密钥失败");
            throw new CommonException(ErrorCode.SECRET_RESET_ERROR);
        }
    }

    @Override
    public String getAppName(Long appId) {
        return appMapper.selectAppName(appId);
    }

}