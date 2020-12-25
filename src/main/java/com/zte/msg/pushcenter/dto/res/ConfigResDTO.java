package com.zte.msg.pushcenter.dto.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:59
 */
@Data
@ApiModel
public class ConfigResDTO {

    private Long id;

    @JsonProperty(value = "config_name")
    private String configName;

    private String description;

    @JsonProperty(value = "provider_name")
    private String providerName;

}
