package com.zte.msg.pushcenter.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/22 14:34
 */
@Data
@ApiModel
public class SmsProviderConfigResDTO {

    private Long id;

    @ApiModelProperty(value = "供应商id")
    @JsonProperty(value = "provider_id")
    private Long providerId;

    @ApiModelProperty(value = "接口请求方法")
    @JsonProperty(value = "m_req")
    private String mReq;

    @ApiModelProperty(value = "请求的基础url")
    private String baseUrl;

    /**
     * 接口电话号码字段名
     */
    @ApiModelProperty(value = "接口电话号码字段名")
    private String pPhoneNum;

    /**
     * 接口模版id字段名
     */
    @ApiModelProperty(value = "接口模版id字段名")
    private String pTemId;

    /**
     * 接口模版值字段名
     */
    @ApiModelProperty(value = "接口模版值字段名")
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
    private String mAuth;
}
