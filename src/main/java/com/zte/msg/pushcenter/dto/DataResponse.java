package com.zte.msg.pushcenter.dto;

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

    private DataResponse(T data, AppStatus state) {
        super(state);
        this.data = data;
    }

    public static <T> DataResponse<T> of(T data) {
        return new DataResponse<>(data,null);
    }

    public static <T> DataResponse<T> success() {
        return new DataResponse<T>(null, AppStatus.SUCCESS);
    }

    public static <T> DataResponse<T> error(){
        return new DataResponse<>(null, AppStatus.PARAM_EMPTY);
    }

}
