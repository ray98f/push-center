package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.entity.App;
import com.zte.msg.pushcenter.service.AppService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * @date 2020/12/30 17:03
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "服务管理")
@Validated
public class AppController {

    @Resource
    private AppService appService;

    /**
     * 服务列表获取
     *
     * @return List<App>
     */
    @GetMapping("/app")
    @ApiOperation(value = "服务列表获取")
    public DataResponse<List<App>> listApp(){
        return DataResponse.of(appService.listApp());
    }

    /**
     * 服务删除
     *
     * @param appId 服务ID
     * @return <T>
     */
    @DeleteMapping("/app")
    @ApiOperation(value = "服务删除")
    public <T> DataResponse<T> deleteApp(@Valid @RequestParam @NotNull(message = "32000006") Integer appId){
        appService.deleteApp(appId);
        return DataResponse.success();
    }

    /**
     * 服务修改
     *
     * @param app 服务信息
     * @return <T>
     */
    @PostMapping("/app")
    @ApiOperation(value = "服务修改")
    public <T> DataResponse<T> updateApp(@RequestBody @Valid App app){
        appService.updateApp(app);
        return DataResponse.success();
    }

    /**
     * 新增服务
     *
     * @param appName 服务名称
     * @return <T>
     */
    @PutMapping("/app")
    @ApiOperation(value = "新增服务")
    public <T> DataResponse<T> insertApp(@Valid @RequestParam @NotNull(message = "32000006") String appName){
        appService.insertApp(appName);
        return DataResponse.success();
    }

}
