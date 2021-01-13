package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;


/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/5 12:37
 */
@Data
@ApiModel
public class ProviderSmsTemplateResDTO {

    @ApiModelProperty(value = "消息平台模版id")
    private Long id;

    @ApiModelProperty(value = "关联关系id")
    private Long relationId;

    @ApiModelProperty(value = "供应商短信模版编号")
    private String code;

    @ApiModelProperty(value = "短信签名")
    private String sign;

    @ApiModelProperty(value = "短信内容")
    private String content;

    @ApiModelProperty(value = "状态 0 禁用 1 启用")
    private Integer status;

    @ApiModelProperty(value = "优先级，数值越大优先级越高")
    private Integer priority;

    @ApiModelProperty(value = "操作人")
    private String updatedBy;

    @ApiModelProperty(value = "操作时间")
    private Timestamp updatedAt;


}
