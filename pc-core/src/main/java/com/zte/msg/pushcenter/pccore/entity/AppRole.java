package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.dto.res.TemplateResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.WeChatTemplateRoleResDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class AppRole extends SendMode {

    @ApiModelProperty(value = "应用模板权限Id")
    private Integer appRoleId;

    @ApiModelProperty(value = "发送方式Id")
    private Integer modeId;

    @ApiModelProperty(value = "应用Id")
    private Integer appId;

    @ApiModelProperty(value = "发送方式启用状态")
    private Integer modeStatus;

    @ApiModelProperty(value = "微信公众号模板信息")
    private List<WeChatTemplateRoleResDTO> wechatTemplate;

    @ApiModelProperty(value = "短信模板信息")
    private List<TemplateResDTO> smsTemplate;
}
