package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 16:18
 */
@Data
public class JuheSmsReqDTO {

    private String mobile;

    @JsonProperty(value = "tpl_id")
    private String tplId;

    @JsonProperty(value = "tpl_value")
    private String tplValue;

    private String ext;

    private String dtype;

    private String key;


}
