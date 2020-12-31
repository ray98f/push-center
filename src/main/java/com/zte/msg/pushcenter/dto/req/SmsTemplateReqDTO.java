package com.zte.msg.pushcenter.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:54
 */
@Data
@ApiModel
public class SmsTemplateReqDTO {

    @ApiModelProperty(value = "短信模版名称")
    @NotNull(message = "32000006")
    private String name;

    @ApiModelProperty(value = "短信模版描述")
    private String description;

    @ApiModelProperty(value = "相关短信模版列表")
    private List<ProviderSmsTemplateReqDTO> providerSmsTemplates;

}
