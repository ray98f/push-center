package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.PageReqDTO;
import com.zte.msg.pushcenter.pccore.dto.PageResponse;
import com.zte.msg.pushcenter.pccore.dto.req.ProviderReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.ProviderResDTO;
import com.zte.msg.pushcenter.pccore.service.ProviderService;
import com.zte.msg.pushcenter.pccore.annotation.PermissionCheck;
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
@Api(tags = "消息平台管理")
@Validated
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @PostMapping
    @PermissionCheck(permissionName = "provider:config:add")
    @ApiOperation(value = "【消息平台配置】- 添加")
    public <T> DataResponse<T> addProvider(@RequestBody @Valid ProviderReqDTO provider) {
        providerService.addProvider(provider);
        return DataResponse.success();
    }

    @PutMapping(value = "/{id}")
    @PermissionCheck(permissionName = "provider:config:modify")
    @ApiOperation(value = "【消息平台配置】- 修改")
    public <T> DataResponse<T> updateProvider(@PathVariable Long id,
                                              @RequestBody ProviderReqDTO provider) {
        providerService.updateProvider(id, provider);
        return DataResponse.success();
    }

    @DeleteMapping
    @ApiOperation(value = "【消息平台配置】- 删除")
    @PermissionCheck(permissionName = "provider:config:remove")
    public <T> DataResponse<T> deleteProvider(@RequestBody Long[] ids) {
        providerService.deleteProvider(ids);
        return DataResponse.success();
    }


    @GetMapping(value = "/page")
    @PermissionCheck(permissionName = "provider:config:list")
    @ApiOperation(value = "【消息平台配置】- 分页查询")
    public PageResponse<ProviderResDTO> getProviders(@RequestParam(required = false)
                                                     @ApiParam("平台模糊查询") String provider,
                                                     @RequestParam(required = false)
                                                     @ApiParam("类型") Integer type,
                                                     @RequestParam(required = false)
                                                     @ApiParam("状态") Integer status,
                                                     @Valid PageReqDTO pageReqDTO) {
        return PageResponse.of(providerService.getProviders(provider, type, status, pageReqDTO));
    }
}
