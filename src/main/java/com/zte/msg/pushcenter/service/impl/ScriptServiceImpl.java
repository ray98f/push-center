package com.zte.msg.pushcenter.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.entity.Script;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.mapper.ScriptMapper;
import com.zte.msg.pushcenter.service.ProviderService;
import com.zte.msg.pushcenter.service.ScriptService;
import com.zte.msg.pushcenter.utils.Constants;
import com.zte.msg.pushcenter.utils.FileUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Objects;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/24 16:28
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ScriptServiceImpl extends ServiceImpl<ScriptMapper, Script> implements ScriptService {

    @Resource
    private ProviderService providerService;

    @Override
    public void addScript(ScriptReqDTO reqDTO, MultipartFile file) {
        String context;
        try {
            context = FileUtil.readStringFromFile(file);
        } catch (IOException e) {
            throw new CommonException(ErrorCode.RESOURCE_INIT_ERROR);
        }
        Script script = new Script();
        BeanUtils.copyProperties(reqDTO, script);
        script.setContext(context);
        getBaseMapper().insert(script);
    }

    @Override
    public Page<ScriptResDTO> getScripts(PageReqDTO pageReqDTO) {

        return getBaseMapper().selectByPage(pageReqDTO.of());
    }

    @Override
    public void relate(Long providerId, Long scriptId) {

        Script script = getBaseMapper().selectById(scriptId);
        if (Objects.isNull(script)) {
            throw new CommonException(ErrorCode.SCRIPT_NOT_EXIST);
        }
        if (Objects.isNull(providerService.getProviderById(providerId))) {
            throw new CommonException(ErrorCode.PROVIDER_NOT_EXIST);
        }
        script.setScriptTag("Script" + System.currentTimeMillis() + "_" + providerId);
        script.setProviderId(providerId);
        script.setRelated(Constants.ALREADY_RELATED);
        getBaseMapper().updateById(script);
    }

    @Override
    public void deleteScript(Long id) {
        if (Objects.isNull(getBaseMapper().selectById(id))) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        getBaseMapper().deleteById(id);
    }
}
