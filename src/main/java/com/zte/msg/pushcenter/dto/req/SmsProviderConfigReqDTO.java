package com.zte.msg.pushcenter.dto.req;

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
 * @date 2020/12/22 14:27
 */
@Data
@ApiModel
public class SmsProviderConfigReqDTO {

    @ApiModelProperty(value = "供应商id")
    @JsonProperty(value = "provider_id")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(value = "接口请求方法")
    @JsonProperty(value = "m_req")
    @NotNull(message = "32000006")
    private String mReq;

    @ApiModelProperty(value = "请求的基础url")
    @JsonProperty(value = "base_url")
    @NotNull(message = "32000006")
    private String baseUrl;

    /**
     * 接口电话号码字段名
     */
    @ApiModelProperty(value = "接口电话号码字段名")
    @JsonProperty(value = "p_phone_num")
    @NotNull(message = "32000006")
    private String pPhoneNum;

    /**
     * 接口模版id字段名
     */
    @ApiModelProperty(value = "接口模版id字段名")
    @JsonProperty(value = "p_tem_id")
    @NotNull(message = "32000006")
    private String pTemId;

    /**
     * 接口模版值字段名
     */
    @ApiModelProperty(value = "接口模版值字段名")
    @JsonProperty(value = "p_tem_value")
    @NotNull(message = "32000006")
    private String pTemValue;

    /**
     * 接口扩展字段，Json字符串
     */
    @ApiModelProperty(value = "接口扩展字段，如果有指定的值，请求为键值对，Json字符串")
    private String pExt;

    /**
     * 接口签名加密方法
     */
    @ApiModelProperty(value = "接口签名加密方法")
    @JsonProperty(value = "m_sign")
    private String mSign;

}
