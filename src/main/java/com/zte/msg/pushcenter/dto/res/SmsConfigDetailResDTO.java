package com.zte.msg.pushcenter.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zte.msg.pushcenter.utils.AesUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/28 16:21
 */
@Data
@ApiModel
public class SmsConfigDetailResDTO {

    private Long id;

    @ApiModelProperty(value = "配置名称")
    @JsonProperty(value = "config_name")
    private String configName;

    @ApiModelProperty(value = "基础配置表id")
    @JsonProperty(value = "config_id")
    private Long configId;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "第三方短信服务配置的appId")
    @JsonProperty(value = "app_id")
    private String sAppId;

    @ApiModelProperty(value = "第三方短信服务配置的secretId")
    @JsonProperty(value = "secret_id")
    private String secretId;

    @ApiModelProperty(value = "第三方短信服务配置的secretKey，需加密")
    @JsonProperty(value = "secret_key")
    private String secretKey;

    @JsonProperty(value = "provider_name")
    @ApiModelProperty(value = "供应商")
    private String providerName;

    public void decrypt() {
        if (StringUtils.isNotBlank(secretId)) {
            secretId = AesUtils.decrypt(secretId);
        }
        if (StringUtils.isNotBlank(secretKey)) {
            secretKey = AesUtils.decrypt(secretKey);
        }
    }
}
