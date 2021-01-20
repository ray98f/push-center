package com.zte.msg.pushcenter.pccore.model;

import com.zte.msg.pushcenter.pccore.entity.User;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/20 15:26
 */
@Data
public class EarlyWarnConfigModel {


    @ApiModelProperty(value = "预警周期")
    private Long alarmCycle;

    @ApiModelProperty(value = "阈值")
    private Integer threshold;

    @ApiModelProperty(value = "报警间隔")
    private Long alarmInterval;

    @ApiModelProperty(value = "处置人员")
    private String userIds;

    @ApiModelProperty(value = "短信模板")
    private Long smsTemplateId;

    @ApiModelProperty(value = "邮件标题")
    private String mailTitle;

    @ApiModelProperty(value = "邮件内容")
    private String mailBody;

    @ApiModelProperty(value = "邮箱，消息平台id")
    private Long mailProviderId;

    @ApiModelProperty(value = "公众号，消息平台id")
    private Long wechatProviderId;

    @ApiModelProperty(value = "公众号模板id")
    private Long wechatTemplateId;

    @ApiModelProperty(value = "公众号内容")
    private String wechatData;

    @ApiModelProperty(value = "AppId")
    private Long appId;

    @ApiModelProperty(value = "目标微信openId")
    private String openIds;

    private List<User> users;

}
