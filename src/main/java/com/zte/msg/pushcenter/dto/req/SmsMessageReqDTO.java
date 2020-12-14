package com.zte.msg.pushcenter.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.bind.annotation.RequestMapping;

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
//    @Pattern(regexp = "\\s*|(((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$)",
//            message = "手机号码不合法")
    @JsonProperty(value = "phone_nums")
    private String[] phoneNums;

    @ApiModelProperty(value = "模版id", required = true)
    @NotNull(message = "32000006")
    @JsonProperty(value = "template_id")
    private String templateId;

    @ApiModelProperty(value = "变量列表, 例如: code=10000")
    private String[] var;

    @ApiModelProperty(value = "服务提供商", required = true)
    private String provider;

    @ApiModelProperty(value = "分配的secretId")
    @JsonProperty(value = "secret_Id")
    private String secretId;

    @ApiModelProperty(value = "secretKey或appKey填在这个字段里", required = true)
    @JsonProperty(value = "secret_key")
    private String secretKey;

    @ApiModelProperty(value = "第三方服务商配置时生成的ApplicationId")
    private String application;

    @ApiModelProperty(value = "短信签名")
    private String sign;

    /**
     * 调用Auth接口返回的token
     */
    @ApiModelProperty(value = "调用鉴权接口获取的token")
    private String token;

}
