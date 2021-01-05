package com.zte.msg.pushcenter.pccore.dto;

import lombok.Data;

@Data
public class SimpleTokenInfo {
    private String userId;
    private String userName;
    private String userRole;

    public SimpleTokenInfo(){}

    public SimpleTokenInfo(String userId, String userName, String userRole) {
        this.userId = userId;
        this.userName = userName;
        this.userRole = userRole;
    }

    public SimpleTokenInfo(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }
}
