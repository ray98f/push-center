package com.zte.msg.pushcenter.dto.res;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/21 10:59
 */
@Data
@ApiModel
public class ProviderResDTO {

    private Long id;

    private String providerName;

    private Integer type;

    private String secretId;

    private String secretKey;

    private String appId;

}
