package com.zte.msg.pushcenter.dto.res;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 13:33
 */
@Data
@ApiModel
public class SmsTemplateResDTO {

    private Long id;

    @ApiModelProperty(value = "供应商id")
    private Integer smsConfigId;

    @ApiModelProperty(value = "模版id")
    private String templateId;

    private String example;
}
