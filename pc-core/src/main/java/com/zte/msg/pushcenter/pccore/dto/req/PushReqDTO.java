package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 10:20
 */
@Data
@ApiModel
public class PushReqDTO {

    @ApiModelProperty(value = "雪花算法生成的64位整形", required = true)
    @NotNull(message = "32000006")
    private String messageId;

    @ApiModelProperty(value = "调用者id", required = true)
    @NotNull(message = "32000006")
    private Integer appId;

    @ApiModelProperty(value = "是否需要回调, true or false")
    @NotNull(message = "32000006")
    private Boolean isCallBack;

    @ApiModelProperty(value = "回调地址")
    private String callBackUrl;

    @ApiModelProperty(value = "发起请求时间戳")
    private String requestTime;

    @ApiModelProperty(value = "签名sign值")
    private String sign;

}
