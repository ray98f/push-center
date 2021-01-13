package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.TemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WechatTemplateResDTO;
import com.zte.msg.pushcenter.pccore.entity.AppRole;
import com.zte.msg.pushcenter.pccore.entity.SendMode;
import com.zte.msg.pushcenter.pccore.entity.SmsTemplate;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.AppRoleMapper;
import com.zte.msg.pushcenter.pccore.service.AppRoleService;
import com.zte.msg.pushcenter.pccore.service.TemplateService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class AppRoleServiceImpl implements AppRoleService {

    @Autowired
    private AppRoleMapper appRoleMapper;

    @Autowired
    private TemplateService templateService;

    @Override
    public AppRoleResDTO listAppRole(Integer appId) {
        AppRoleResDTO appRoleResDTO = appRoleMapper.selectApp(appId);
        if (Objects.nonNull(appId)) {
            log.info("服务查询成功");
            List<AppRole> appRole = appRoleMapper.selectAppMode(appId);
            for (AppRole appRoleTemplate : appRole) {
                if (appRoleTemplate.getModeId() == 1) {
                    List<TemplateResDTO> templateResDTOList = appRoleMapper.selectSmsTemplate(appRoleTemplate.getModeId(), appId);
                    List<SmsTemplate> smsTemplateList = templateService.getTemplateList();
                    if (Objects.isNull(smsTemplateList) || 0 == smsTemplateList.size()) {
                        throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
                    }
                    if (Objects.isNull(templateResDTOList) || 0 == templateResDTOList.size()) {
                        for (SmsTemplate smsTemplate : smsTemplateList) {
                            TemplateResDTO resDTO = new TemplateResDTO();
                            resDTO.setId(smsTemplate.getId());
                            resDTO.setContent(smsTemplate.getContent());
                            templateResDTOList.add(resDTO);
                        }
                        appRoleTemplate.setSmsTemplate(templateResDTOList);
                        continue;
                    }
                    List<TemplateResDTO> resultTemplateResDTOList = new ArrayList<>(templateResDTOList);
                    List<SmsTemplate> resultSmsTemplateList = new ArrayList<>(smsTemplateList);
                    if (templateResDTOList.size() < smsTemplateList.size()) {
                        for (SmsTemplate smsTemplate : smsTemplateList) {
                            for (TemplateResDTO templateResDTO : templateResDTOList) {
                                if (smsTemplate.getId().equals(templateResDTO.getId())) {
                                    resultSmsTemplateList.remove(smsTemplate);
                                }
                            }
                        }
                        if (resultSmsTemplateList.size() > 0) {
                            for (SmsTemplate resultSmsTemplate : resultSmsTemplateList) {
                                TemplateResDTO resDTO = new TemplateResDTO();
                                resDTO.setId(resultSmsTemplate.getId());
                                resDTO.setContent(resultSmsTemplate.getContent());
                                resultTemplateResDTOList.add(resDTO);
                            }
                        }
                    }
                    appRoleTemplate.setSmsTemplate(resultTemplateResDTOList);
                }
                if (appRoleTemplate.getModeId() == 4) {
                    List<WechatTemplateResDTO> wechatTemplateResDTOList = appRoleMapper.selectWechatTemplate(appId);
                    if (Objects.isNull(wechatTemplateResDTOList) || 0 == wechatTemplateResDTOList.size()) {
                        appRoleTemplate.setWechatTemplate(null);
                        continue;
                    }
                    appRoleTemplate.setWechatTemplate(wechatTemplateResDTOList);
                }
            }
            appRoleResDTO.setSendMode(appRole);
            return appRoleResDTO;
        } else {
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public void editAppRole(AppRoleResDTO appRoleResDTO) {
        if (Objects.isNull(appRoleResDTO)) {
            log.error("app权限修改参数返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.editAppRole(appRoleResDTO);
        if (result > 0) {
            log.info("编辑app权限成功");
        } else {
            log.error("编辑app权限失败");
            throw new CommonException(ErrorCode.APP_ROLE_EDIT_ERROR);
        }
    }

    @Override
    public List<SendMode> listSendMode() {
        List<SendMode> sendModeList = appRoleMapper.listSendMode();
        if (null != sendModeList && !sendModeList.isEmpty()) {
            log.info("权限列表获取成功");
            return sendModeList;
        } else {
            log.error("权限列表获取失败");
            throw new CommonException(ErrorCode.SELECT_EMPTY);
        }
    }

    @Override
    public void deleteSendMode(Integer modeId) {
        if (Objects.isNull(modeId)) {
            log.error("权限ID返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.deleteSendMode(modeId);
        if (result > 0) {
            log.info("权限删除成功");
        } else {
            log.error("权限删除失败");
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }

    @Override
    public void updateSendMode(SendMode sendMode) {
        if (Objects.isNull(sendMode)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = appRoleMapper.updateSendMode(sendMode);
        if (result > 0) {
            log.info("权限修改成功");
        } else {
            log.error("权限修改失败");
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void insertSendMode(String modeName) {
        if (StringUtils.isBlank(modeName)) {
            log.error("权限信息返回为空");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Integer id = appRoleMapper.selectSendModeId(modeName);
        if (Objects.nonNull(id)) {
            log.error("权限已存在");
            throw new CommonException(ErrorCode.DATA_EXIST);
        }
        int result = appRoleMapper.insertSendMode(modeName);
        if (result > 0) {
            log.info("添加权限成功");
        } else {
            log.error("添加权限失败");
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }
}