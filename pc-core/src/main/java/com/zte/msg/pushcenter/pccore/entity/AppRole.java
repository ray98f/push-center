package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.dto.res.TemplateResDTO;
import lombok.Data;

import java.util.List;

@Data
public class AppRole extends Role {

    private Integer roleId;

    private Integer appId;

    private List<TemplateResDTO> template;


}
