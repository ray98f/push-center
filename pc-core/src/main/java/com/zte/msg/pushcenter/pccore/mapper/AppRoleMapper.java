package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.TemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WeChatTemplateRoleResDTO;
import com.zte.msg.pushcenter.pccore.entity.AppRole;
import com.zte.msg.pushcenter.pccore.entity.SendMode;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface AppRoleMapper extends BaseMapper<AppRoleResDTO> {

    /**
     * 搜索应用
     * @param appId
     * @return
     */
    AppRoleResDTO selectApp(Integer appId);

    /**
     * 获取短信模板
     * @param modeId
     * @param appId
     * @return
     */
    List<TemplateResDTO> selectSmsTemplate(Integer modeId, Integer appId);

    /**
     * 获取公众号模板
     * @param appId
     * @return
     */
    List<WeChatTemplateRoleResDTO> selectWechatTemplate(Integer appId);

    /**
     * 获取所有公众号账号
     * @return
     */
    List<WeChatTemplateRoleResDTO> listAllWechatProvider();

    /**
     * 获取应用权限
     * @param appId
     * @return
     */
    List<AppRole> selectAppMode(Integer appId);

    /**
     * 修改应用权限
     * @param appRoleResDTO
     * @return
     */
    int editAppRole(AppRoleResDTO appRoleResDTO);

    /**
     * 获取消息推送方式列表
     * @return
     */
    List<SendMode> listSendMode();

    /**
     * 删除消息推送方式
     * @param modeId
     * @return
     */
    int deleteSendMode(Integer modeId);

    /**
     * 修改消息推送方式
     * @param sendMode
     * @return
     */
    int updateSendMode(SendMode sendMode);

    /**
     * 新增消息推送方式
     * @param modeName
     * @return
     */
    int insertSendMode(String modeName);

    /**
     * 获取消息推送方式id
     * @param modeName
     * @return
     */
    Integer selectSendModeId(String modeName);

    /**
     * 应用模板权限删除
     * @param providerIds
     * @return
     */
    int deleteAppRole(Long[] providerIds);
}
