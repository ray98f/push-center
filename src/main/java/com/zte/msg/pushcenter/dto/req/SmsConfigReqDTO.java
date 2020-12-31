package com.zte.msg.pushcenter.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zte.msg.pushcenter.utils.AesUtils;
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
    @JsonProperty(value = "config_name")
    private String configName;

    @ApiModelProperty(value = "描述")
    @Length(max = 60, message = "32000003")
    private String description;

    @ApiModelProperty(value = "基础配置表id")
    @JsonProperty(value = "config_id")
    @NotNull(message = "32000006")
    private Long configId;

    @ApiModelProperty(value = "第三方短信服务配置的appId")
    @JsonProperty(value = "s_app_id")
    private String sAppId;

    @ApiModelProperty(value = "第三方短信服务配置的secretId")
    @JsonProperty(value = "secret_id")
    private String secretId;

    @ApiModelProperty(value = "第三方短信服务配置的secretKey")
    @JsonProperty(value = "secret_key")
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
