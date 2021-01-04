package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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
    @Pattern(regexp = "\\s*|(((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$)",
            message = "手机号码不合法")
    @JsonProperty(value = "phone_num")
    private String phoneNum;

    @ApiModelProperty(value = "模版id", required = true)
    @NotNull(message = "32000006")
    @JsonProperty(value = "template_id")
    private Long templateId;

    @ApiModelProperty(value = "模版变量键值对")
    private Map<String, String> vars;

}
