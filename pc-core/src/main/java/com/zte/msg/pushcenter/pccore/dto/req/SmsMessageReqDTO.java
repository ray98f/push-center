package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 10:05
 */
@ApiModel
@Data
public class SmsMessageReqDTO extends PushReqDTO {

    @ApiModelProperty(value = "手机号码", required = true)
    @NotNull(message = "32000006")
    private String[] phoneNum;

    @ApiModelProperty(value = "模版id", required = true)
    @NotNull(message = "32000006")
    private Long templateId;

    @ApiModelProperty(value = "模版变量键值对")
    private Map<String, String> vars;

}
