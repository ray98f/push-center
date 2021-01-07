package com.zte.msg.pushcenter.pccore.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/4 15:59
 */
@Data
@ApiModel
public class SmsTemplateDetailResDTO {

    @ApiModelProperty(value = "模版id")
    private Long id;

    @ApiModelProperty(value = "模版内容")
    private String content;

    @ApiModelProperty(value = "参数列表")
    private String params;

    @ApiModelProperty(value = "消息平台")
    private String providers;

    @ApiModelProperty(value = "状态：0-禁用，1-启用")
    private Integer status;

    @ApiModelProperty(value = "操作人")
    @JsonProperty
    private String updatedBy;

    @ApiModelProperty(value = "操作时间")
    @JsonProperty
    private Timestamp updatedAt;

    @ApiModelProperty(value = "关联短信模版列表")
    @JsonProperty
    private List<ProviderSmsTemplateResDTO> providerSmsTemplates;
}
