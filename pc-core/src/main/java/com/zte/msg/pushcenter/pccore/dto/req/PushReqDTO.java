package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
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
    @JsonProperty(value = "message_id")
    private String messageId;

    @ApiModelProperty(value = "调用者id", required = true)
    @NotNull(message = "32000006")
    @JsonProperty(value = "app_id")
    private Long appId;

    @ApiModelProperty(value = "是否需要回调, true or false")
    @NotNull(message = "32000006")
    @JsonProperty(value = "is_call_back")
    private Boolean isCallBack;

    @ApiModelProperty(value = "回调地址")
    @JsonProperty(value = "call_back_url")
    private String callBackUrl;

}
