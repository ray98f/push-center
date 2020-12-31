package com.zte.msg.pushcenter.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import org.springframework.web.multipart.MultipartFile;

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

    void relate(Long configId, Long scriptId);

    void deleteScript(Long id);


}
