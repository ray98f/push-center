package com.zte.msg.pushcenter.dto;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.Collections;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 9:49
 */
@Data
@ApiModel
public class PageReqDTO {

    @Min(value = 1L, message = "32000008")
    @ApiModelProperty(value = "当前页码", required = true)
    private Long currentPage;

    @Range(min = 1, max = 50, message = "32000003")
    @ApiModelProperty(value = "每页条数。范围：1-50", required = true)
    private Long pageSize;

    @Size(max = 128, message = "32000003")
    @ApiModelProperty(value = "排序字段。长度：0-128")
    private String sort;

    @ApiModelProperty(value = "顺序,asc: 升序，desc")
    private String order;

    public <T> Page<T> of() {
        if (null != sort && null != order) {
            Page<T> page = new Page<>(this.getCurrentPage(), this.getPageSize());
            page.setOrders(Collections.singletonList(new OrderItem().setAsc("asc".equals(order.toLowerCase())).setColumn(sort)));
            return page;
        }
        return new Page<>(this.getCurrentPage(), this.getPageSize());
    }

}
