package com.zte.msg.pushcenter.exception;

import com.zte.msg.pushcenter.enums.ErrorCode;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/4/22 15:52
 */
@Data
public class CommonException extends RuntimeException {

    private Integer code;

    private String message;

    public CommonException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public CommonException(ErrorCode error) {
        this.code = error.code();
        this.message = error.message();
    }

}
