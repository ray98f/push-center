package com.zte.msg.pushcenter.pccore.entity;

import com.zte.msg.pushcenter.pccore.dto.res.TemplateResDTO;
import lombok.Data;

import java.util.List;

/**
 * @author frp
 */
@Data
public class AppRole extends SendMode {

    private Integer appRoleId;

    private Integer modeId;

    private Integer appId;

    private Integer modeStatus;

    private List<TemplateResDTO> template;


}
