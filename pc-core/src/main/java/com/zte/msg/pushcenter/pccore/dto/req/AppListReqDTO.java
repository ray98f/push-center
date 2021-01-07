package com.zte.msg.pushcenter.pccore.dto.req;

import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import lombok.Data;

@Data
public class AppListReqDTO extends PageReqDTO {
    private Integer appId;

    private String appName;

    private Integer status;

}
