package com.zte.msg.pushcenter.pccore.dto;

import com.zte.msg.pushcenter.pccore.utils.ObjectUtils;
import lombok.Data;

@Data
public class SignView {
    private Object sendMsg;

    private String now;

    private String appId;

    private String url;

    private String sign;

    public SignView() {
    }

    public SignView(Object sendMsg, String now, String appId, String url, String sign) {
        this.sendMsg = sendMsg;
        this.now = now;
        this.appId = appId;
        this.url = url;
        this.sign = sign;
    }

    @Override
    public String toString() {
        return ObjectUtils.getFiledValues(this.sendMsg) + this.now + this.appId + this.url;
    }
}
