package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.PageReqDTO;
import com.zte.msg.pushcenter.dto.PageResponse;
import com.zte.msg.pushcenter.dto.req.ConfigReqDTO;
import com.zte.msg.pushcenter.dto.req.ScriptReqDTO;
import com.zte.msg.pushcenter.dto.res.ConfigResDTO;
import com.zte.msg.pushcenter.service.ConfigService;
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
 * @date 2020/12/21 9:37
 */
@RestController
@RequestMapping("/api/v1/conf")
@Api(tags = "基本配置")
@Validated
public class ConfigController {


    @Resource
    private ConfigService configService;


    @PostMapping
    @ApiOperation(value = "添加第三方服务基本配置")
    public DataResponse<ConfigResDTO> addConfig(@RequestBody @Valid @ApiParam(value = "添加一条基本配置") ConfigReqDTO config) {

        return DataResponse.of(configService.addConfig(config));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "修改第三方服务基本配置")
    public DataResponse<ConfigResDTO> updateConfig(@PathVariable Long id,
                                                   @RequestBody ConfigReqDTO config) {

        return DataResponse.of(configService.updateConfig(id, config));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除第三方服务基本配置")
    public <T> DataResponse<T> deleteConfig(@PathVariable Long id) {
        configService.deleteConfig(id);
        return DataResponse.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取第三方服务基本配置详情")
    public DataResponse<ConfigResDTO> getConfigById(@PathVariable Long id) {
        return DataResponse.of(configService.getConfigById(id));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询第三方服务基本配置")
    public PageResponse<ConfigResDTO> getConfigs(@Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(configService.getConfigs(pageReqDTO));
    }



}
