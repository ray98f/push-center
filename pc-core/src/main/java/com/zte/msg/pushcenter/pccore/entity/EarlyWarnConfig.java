package com.zte.msg.pushcenter.pccore.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
public class EarlyWarnConfig extends BaseEntity{

    @ApiModelProperty(value = "预警周期")
    private Long cycle;

    @ApiModelProperty(value = "阈值")
    private Long threshold;

    @ApiModelProperty(value = "报警间隔")
    private Long interval;

    @ApiModelProperty(value = "处置人员")
    private String userId;

    @ApiModelProperty(value = "短信模板")
    private Integer smsTemplateId;

    @ApiModelProperty(value = "邮件标题")
    private String mailTitle;

    @ApiModelProperty(value = "邮件内容")
    private String mailBody;

    @ApiModelProperty(value = "公众号id")
    private Integer wechatId;

    @ApiModelProperty(value = "公众号名称")
    private String wechatName;

    @ApiModelProperty(value = "公众号模板id")
    private Integer wechatTemplateId;

    @ApiModelProperty(value = "AppId")
    private String applicationId;
}
