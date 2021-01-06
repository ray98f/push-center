package com.zte.msg.pushcenter.pccore.dto.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class SmsHistoryReqDTO extends PageReqDTO {

    private Integer appId;

    private String appName;

    private String phoneNum;

    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date startTime;

    @DateTimeFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-DD HH:mm:ss")
    private Date endTime;

    private Integer result;
}
