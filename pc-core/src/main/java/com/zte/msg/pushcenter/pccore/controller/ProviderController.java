package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
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
@RequestMapping("/api/v1/provider")
@Api(tags = "服务商配置")
@Validated
public class ProviderController {


    @Resource
    private ProviderService providerService;


    @PostMapping
    @ApiOperation(value = "添加第三方服务基本配置")
    public DataResponse<ProviderResDTO> addProvider(@RequestBody @Valid @ApiParam(value = "添加一条基本配置") ProviderReqDTO provider) {

        return DataResponse.of(providerService.addProvider(provider));
    }

    @PutMapping(value = "/{id}")
    @ApiOperation(value = "修改第三方服务基本配置")
    public DataResponse<ProviderResDTO> updateProvider(@PathVariable Long id,
                                                     @RequestBody ProviderReqDTO provider) {

        return DataResponse.of(providerService.updateProvider(id, provider));
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "删除第三方服务基本配置")
    public <T> DataResponse<T> deleteProvider(@PathVariable Long id) {
        providerService.deleteProvider(id);
        return DataResponse.success();
    }

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "根据id获取第三方服务基本配置详情")
    public DataResponse<ProviderResDTO> getProviderById(@PathVariable Long id) {
        return DataResponse.of(providerService.getProviderById(id));
    }

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询第三方服务基本配置")
    public PageResponse<ProviderResDTO> getProviders(@Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(providerService.getProviders(pageReqDTO));
    }



}
