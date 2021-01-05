package com.zte.msg.pushcenter.dto.req;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    /**
     * 公众号appId
     */
    @ApiModelProperty(value = "公众号appId", required = true)
    @JsonProperty(value = "app_id")
    private String weChatAppId;

    /**
     * 公众号secret
     */
    @JsonProperty(value = "app_secret")
    @ApiModelProperty(value = "公众号secret", required = true)
    private String appSecret;

    /**
     * 接口配置里的EncodingAESKey值
     */
    @JsonProperty(value = "aes_key")
    @ApiModelProperty(value = "公众号配置里的EncodingAESKey值", required = true)
    private String aesKey;

    /**
     * 公众号配置的token
     */
    @JsonProperty(value = "app_token")
    @ApiModelProperty(value = "公众号配置的token", required = true)
    private String appToken;

    /**
     * 目标用户微信
     */
    @JsonProperty(value = "target_wechat_id")
    @ApiModelProperty(value = "目标用户微信", required = true)
    private String targetWeChatId;

    /**
     * 模版id
     */
    @JsonProperty(value = "template_id")
    @ApiModelProperty(value = "模版id", required = true)
    private String templateId;

    /**
     * 参数
     */
    @ApiModelProperty(value = "模版参数")
    private JSONObject var;


}
