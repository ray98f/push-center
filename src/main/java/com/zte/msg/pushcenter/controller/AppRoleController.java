package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.res.AppRoleResDTO;
import com.zte.msg.pushcenter.service.AppRoleService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/29 10:37
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "第三方服务权限管理")
@Validated
public class AppRoleController {

    @Resource
    private AppRoleService appRoleService;

    /**
     * 第三方服务权限列表获取
     *
     * @return List<AppRoleResDTO>
     */
    @GetMapping("/appRole")
    public DataResponse<List<AppRoleResDTO>> listAppRole() {
        return DataResponse.of(appRoleService.listAppRole());
    }

    /**
     * 获取第三方服务权限详情
     *
     * @param appId appID
     * @return AppRoleResDTO
     */
    @PutMapping("/appRole")
    public DataResponse<AppRoleResDTO> getAppRole(@Valid @RequestParam @NotNull(message = "32000006") Integer appId) {
        return DataResponse.of(appRoleService.selectAppRole(appId));
    }

    /**
     * 编辑第三方服务权限（有则修改，无则新增）
     * @param appRoleResDTOList 第三方服务编辑参数
     * @return <T>
     */
    @PostMapping("/appRole")
    public <T> DataResponse<T> editAppRole(@RequestBody @Valid List<AppRoleResDTO> appRoleResDTOList){
        appRoleService.editAppRole(appRoleResDTOList);
        return DataResponse.success();
    }
}