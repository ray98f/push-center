package com.zte.msg.pushcenter.dto.req;

import lombok.Data;

@Data
public class PasswordReqDTO {

    private String userName;

    private String oldPwd;

    private String newPwd;
}
