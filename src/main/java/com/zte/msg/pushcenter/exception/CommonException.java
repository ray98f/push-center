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

    private String enMessage;

    private String cnMessage;

    public CommonException(Integer code, String enMessage, String cnMessage) {
        this.code = code;
        this.enMessage = enMessage;
        this.cnMessage = cnMessage;
    }

    public CommonException(ErrorCode error) {
        this.code = error.code();
        this.enMessage = error.enMessage();
        this.cnMessage = error.cnMessage();
    }

}
