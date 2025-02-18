package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zte.msg.pushcenter.pccore.utils.AesUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:27
 */
@Data
@ApiModel
public class SmsConfigReqDTO {

    @ApiModelProperty(value = "配置名称")
    @Length(min = 4, max = 30, message = "32000003")
    @JsonProperty(value = "name")
    private String name;

    @ApiModelProperty(value = "描述")
    @Length(max = 60, message = "32000003")
    private String description;

    @ApiModelProperty(value = "供应商id")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "第三方短信服务配置的appId")
    private String sAppId;

    @ApiModelProperty(value = "第三方短信服务配置的secretId")
    private String secretId;

    @ApiModelProperty(value = "第三方短信服务配置的secretKey")
    @NotNull(message = "32000006")
    private String secretKey;

    public void encrypt() {
        if (StringUtils.isNotBlank(secretId)) {
            secretId = AesUtils.encrypt(secretId);
        }
        if (StringUtils.isNotBlank(secretKey)) {
            secretKey = AesUtils.encrypt(secretKey);
        }
    }
}
