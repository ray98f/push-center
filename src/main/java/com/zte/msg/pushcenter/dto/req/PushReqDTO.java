package com.zte.msg.pushcenter.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 10:20
 */
@Data
@ApiModel
public class PushReqDTO {

    /**
     * 消息内容
     */
    @ApiModelProperty(value = "内容（如果有，没有则不填）")
    private String content;

    @ApiModelProperty(value = "调用者id", required = true)
    private String appId;

}
