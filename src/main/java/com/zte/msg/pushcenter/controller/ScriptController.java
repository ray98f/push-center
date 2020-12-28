package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.PageResponse;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ScriptResDTO;
import com.zte.msg.pushcenter.service.ScriptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/28 14:51
 */
@RestController
@RequestMapping("/api/v1/script")
@Api(tags = "脚本")
@Validated
public class ScriptController {

    @Resource
    private ScriptService scriptService;

    @PostMapping(value = "/script")
    @ApiOperation(value = "添加脚本")
    public <T> DataResponse<T> addScript(@RequestBody ScriptReqDTO script) {

        scriptService.addScript(script);
        return DataResponse.success();
    }

    @PutMapping(value = "/relate")
    @ApiOperation(value = "关联脚本和配置")
    public <T> DataResponse<T> relate(@RequestParam @ApiParam(value = "配置id") Long configId,
                                      @RequestParam @ApiParam(value = "脚本id") Long scriptId) {
        scriptService.relate(configId, scriptId);
        return DataResponse.success();
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页脚本查询 ")
    public PageResponse<ScriptResDTO> getScripts(@Valid PageReqDTO reqDTO) {
        return PageResponse.of(scriptService.getScripts(reqDTO));
    }
}
