package com.zte.msg.pushcenter.pccore.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.pccore.entity.Script;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/24 16:28
 */
public interface ScriptService {

    void addScript(ScriptReqDTO script, MultipartFile context);

    Page<ScriptResDTO> getScripts(PageReqDTO pageReqDTO);

    void relate(Long providerId, Long scriptId);

    void deleteScript(Long id);

    List<Script> getScripts();
}
