package com.zte.msg.pushcenter.pccore.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class CidsReqDTO {

    private String sysCode;

    private List<String> userIds;
}
