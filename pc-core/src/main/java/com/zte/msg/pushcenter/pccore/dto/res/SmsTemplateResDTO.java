package com.zte.msg.pushcenter.pccore.dto.res;

import io.swagger.annotations.ApiModel;
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

    private String name;

    private String description;

}
