package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.entity.Template;
import lombok.Data;

@Data
public class TemplateResDTO extends Template {

    private Integer appRoleId;

    private Integer status;
}
