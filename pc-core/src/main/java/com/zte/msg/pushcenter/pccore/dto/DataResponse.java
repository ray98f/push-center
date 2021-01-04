package com.zte.msg.pushcenter.pccore.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/10/10 15:57
 */
@ApiModel
@Data
public class DataResponse<T> extends BaseResponse implements Serializable {

    private T data;

    private DataResponse(T data) {
        super(AppStatus.SUCCESS);
        this.data = data;
    }

    public static <T> DataResponse<T> of(T data) {
        return new DataResponse<>(data);
    }

    public static <T> DataResponse<T> success() {
        return new DataResponse<T>(null);
    }

}
