package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.entity.SmsTemplate;
import lombok.Data;

@Data
public class TemplateResDTO extends SmsTemplate {

    private Integer appRoleId;
}
