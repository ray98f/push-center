package com.zte.msg.pushcenter.pccore.dto.req;

import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author frp
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class ApplicationHistoryReqDTO extends PageReqDTO {

    @ApiModelProperty(value = "应用id")
    private Integer appId;

    @ApiModelProperty(value = "应用名称")
    private String appName;

    @ApiModelProperty(value = "目标平台")
    private String targetPlatform;

    @ApiModelProperty(value = "推送目标（设备注册id）")
    private String audience;

    @ApiModelProperty(value = "搜索开始时间")
    private String startTime;

    @ApiModelProperty(value = "搜索结束时间")
    private String endTime;

    @ApiModelProperty(value = "发送状态")
    private Integer result;
}
