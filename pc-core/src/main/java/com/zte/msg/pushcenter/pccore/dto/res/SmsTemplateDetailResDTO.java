package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.dto.req.ProviderSmsTemplateReqDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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

    private Integer id;

    private String name;

    private String description;

    @ApiModelProperty(value = "相关短信模版列表")
    private List<ProviderSmsTemplateReqDTO> providerSmsTemplates;
}
