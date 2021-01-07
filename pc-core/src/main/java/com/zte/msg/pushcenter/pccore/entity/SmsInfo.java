package com.zte.msg.pushcenter.pccore.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.util.Date;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/23 9:51
 */
@Data
@ApiModel
public class SmsInfo extends BaseEntity {

    @ApiModelProperty(value = "应用id")
    @JsonProperty(value = "app_id")
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    @JsonProperty(value = "app_name")
    private String appName;

    @ApiModelProperty(value = "发送号码")
    @JsonProperty(value = "phone_num")
    private String phoneNum;

    @ApiModelProperty(value = "发送时间")
    @JsonProperty(value = "transmit_time")
    private Timestamp transmitTime;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "发送平台")
    @JsonProperty(value = "provider_name")
    private String providerName;

    @ApiModelProperty(value = "模板id")
    @JsonProperty(value = "template_id")
    private String templateId;

    @ApiModelProperty(value = "发送状态")
    private Integer result;

    @ApiModelProperty(value = "错误代码")
    @JsonProperty(value = "fail_code")
    private Integer failCode;

    @ApiModelProperty(value = "错误消息")
    @JsonProperty(value = "fail_reason")
    private String failReason;

    @ApiModelProperty(value = "状态时间")
    @JsonProperty(value = "result_time")
    private Timestamp resultTime;
}
