package com.zte.msg.pushcenter.dto.res;

import com.baomidou.mybatisplus.annotation.TableField;
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

    @TableField(value = "provider_name")
    private String providerName;
}
