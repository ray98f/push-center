package com.zte.msg.pushcenter.dto;

import lombok.Data;

@Data
public class TokenInfo {
    private String appId;
    private String appName;
    private String appKey;
    private String appSecret;
    private String role;

    public TokenInfo(){}

    public TokenInfo(String appId, String appName, String appKey, String appSecret, String role) {
        this.appId = appId;
        this.appName = appName;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.role = role;
    }

    public TokenInfo(String appId, String appName, String appKey, String appSecret) {
        this.appId = appId;
        this.appName = appName;
        this.appKey = appKey;
        this.appSecret = appSecret;
    }
}
