package com.zte.msg.pushcenter.dto.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:52
 */
@Data
@ApiModel
public class ProviderReqDTO {

    @JsonProperty(value = "provider_name")
    @NotNull(message = "服务商名")
    private String providerName;
}
