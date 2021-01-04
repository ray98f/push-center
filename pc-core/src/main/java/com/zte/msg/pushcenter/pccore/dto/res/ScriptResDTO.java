package com.zte.msg.pushcenter.pccore.dto.res;

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
 * @date 2020/12/25 16:55
 */
@Data
@ApiModel
public class ScriptResDTO {

    private Long id;

    @JsonProperty(value = "script_tag")
    @ApiModelProperty(value = "脚本唯一标识")
    private String scriptTag;

    @ApiModelProperty(value = "脚本推送类型：1-短信，2-邮件，3-App，4-微信")
    @NotNull(message = "32000006")
    private Integer type;

    @ApiModelProperty(value = "脚本内容")
    private String context;

    @ApiModelProperty(value = "脚本名")
    private String scriptName;

}
