package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/13 17:02
 */
@Data
@ApiModel
public class WeChatTemplateUpdateReqDTO {

    @ApiModelProperty(value = "模版id")
    @NotNull(message = "32000006")
    private Long id;

    @ApiModelProperty(required = true, value = "消息平台id")
    @NotNull(message = "32000006")
    private Long providerId;

    @ApiModelProperty(required = true, value = "公众号模板id")
    @NotNull(message = "32000006")
    private String weChatTemplateId;

    @ApiModelProperty(required = true, value = "标题")
    @NotNull(message = "32000006")
    private String title;

    @ApiModelProperty(required = true, value = "内容")
    @NotNull(message = "32000006")
    private String content;

    @ApiModelProperty(required = true, value = "状态")
    @NotNull(message = "32000006")
    private Integer status;

}
