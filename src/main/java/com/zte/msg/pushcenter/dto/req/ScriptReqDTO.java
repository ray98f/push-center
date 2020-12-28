package com.zte.msg.pushcenter.dto.req;

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

//    @ApiModelProperty(value = "关联的配置id")
//    @NotNull(message = "32000006")
//    private Long configId;

    @ApiModelProperty(value = "脚本内容")
    @NotNull(message = "32000006")
    private String context;

    @ApiModelProperty(value = "脚本描述")
    private String description;

}
