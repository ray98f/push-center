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
 * @date 2020/12/24 16:23
 */
@Data
@ApiModel
public class ScriptReqDTO {

    @ApiModelProperty(value = "脚本名")
    @NotNull(message = "32000006")
    private String scriptName;

    @ApiModelProperty(value = "脚本推送类型：1-短信，2-邮件，3-App，4-微信")
    @NotNull(message = "32000006")
    private Integer type;

    @ApiModelProperty(value = "脚本描述")
    private String description;

}
