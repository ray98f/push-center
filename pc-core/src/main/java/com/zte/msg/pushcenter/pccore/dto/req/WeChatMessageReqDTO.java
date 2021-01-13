package com.zte.msg.pushcenter.pccore.dto.req;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 14:43
 */
@ApiModel
@Data
public class WeChatMessageReqDTO extends PushReqDTO {

    @ApiModelProperty(value = "OpenID", required = true)
    @NotNull(message = "32000006")
    private String openId;

    @ApiModelProperty(value = "公众号providerId")
    @NotNull(message = "32000006")
    private String providerId;

    /**
     * 公众号appId
     */
    @ApiModelProperty(value = "公众号appId", required = true)
    private String weChatAppId;

    /**
     * 公众号secret
     */
    @ApiModelProperty(value = "公众号secret", required = true)
    private String appSecret;

    /**
     * 接口配置里的EncodingAESKey值
     */
    @ApiModelProperty(value = "公众号配置里的EncodingAESKey值", required = true)
    private String aesKey;

    /**
     * 公众号配置的token
     */
    @ApiModelProperty(value = "公众号配置的token", required = true)
    private String appToken;

    /**
     * 目标用户微信
     */
    @ApiModelProperty(value = "目标用户微信", required = true)
    private String targetWeChatId;

    /**
     * 模版id
     */
    @ApiModelProperty(value = "模版id", required = true)
    private String templateId;

    /**
     * 参数
     */
    @ApiModelProperty(value = "模版参数")
    private JSONObject var;


}
