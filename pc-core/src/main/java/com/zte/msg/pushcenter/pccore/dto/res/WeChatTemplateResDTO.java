package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.dto.BaseResDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/13 17:20
 */
@Data
@ApiModel
public class WeChatTemplateResDTO extends BaseResDTO {

    @ApiModelProperty(value = "消息平台名称")
    private String providerName;

    @ApiModelProperty(value = "公众号模板id")
    private String weChatTemplateId;

    @ApiModelProperty(value = "模版标题")
    private String title;

    @ApiModelProperty(value = "模版内容")
    private String content;

    @ApiModelProperty(value = "启用状态")
    private Integer status;

}
