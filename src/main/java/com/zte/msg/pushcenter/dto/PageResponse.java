package com.zte.msg.pushcenter.dto;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/4/22 9:37
 */
@Data
@ApiModel
public class PageResponse<T> extends BaseResponse {

    private PageResponse.PagedData<T> data;

    public static <T> PageResponse<T> of(Page<T> page) {
        PageResponse.PagedData<T> data = new PageResponse.PagedData<>(page);
        return new PageResponse<>(data);
    }

    private PageResponse(PageResponse.PagedData<T> data) {
        super(AppStatus.SUCCESS);
        this.data = data;
    }

    @Data
    public static class PagedData<T> {
        @ApiModelProperty("分页后的内容")
        private List<T> content;

        @ApiModelProperty(value = "当前页面序号", example = "1")
        private Long currentPage;

        @ApiModelProperty(value = "当前页面大小", example = "20")
        private Long pageSize;

        @ApiModelProperty(value = "总页面数", example = "5")
        private Long totalPage;

        @ApiModelProperty(value = "总记录数", example = "100")
        private Long totalCount;

        PagedData(Page<T> page) {
            this.content = page.getRecords();
            this.currentPage = page.getCurrent();
            this.pageSize = page.getSize();
            this.totalPage = page.getPages();
            this.totalCount = page.getTotal();
        }
    }
}
