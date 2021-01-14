package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author frp
 */
@Data
@ApiModel
public class AppStatisticsResDTO extends StatisticsResDTO{

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "短信发送数量")
    private Long smsSendNum;

    @ApiModelProperty(value = "APP发送数量")
    private Long appSendNum;

    @ApiModelProperty(value = "公众号发送数量")
    private Long wechatSendNum;

    @ApiModelProperty(value = "邮件发送数量")
    private Long mailSendNum;
}
