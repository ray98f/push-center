package com.zte.msg.pushcenter.common.pusher.msg.res;

import com.zte.msg.pushcenter.dto.BaseResponse;
import lombok.Data;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 17:18
 */
@Data
public class SmsMessageRes<T> extends BaseResponse {

    private T data;


}
