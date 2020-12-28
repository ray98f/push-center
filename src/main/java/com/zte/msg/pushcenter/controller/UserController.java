package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.entity.User;
import com.zte.msg.pushcenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 14:42
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "用户")
@Validated
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增管理平台登录用户
     * @param user 用户信息
     * @return DataResponse
     */
    @PostMapping("/user")
    @ApiOperation(value = "新增管理平台登录用户")
    public <T> DataResponse<T> creatUser(@RequestBody @Valid User user){
        userService.insertUser(user);
        return DataResponse.success();
    }
}
