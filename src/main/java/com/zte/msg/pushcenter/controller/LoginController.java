package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.entity.User;
import com.zte.msg.pushcenter.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

import static com.zte.msg.pushcenter.utils.TokenUtil.createSimpleToken;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2020/12/28 9:52
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
@Api(tags = "登录")
@Validated
public class LoginController {

    @Resource
    private UserService userService;

    /**
     * 管理平台登录
     * @param user 用户信息
     * @return DataResponse
     * @throws Exception Comm
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理平台登录")
    public DataResponse<Map<String, Object>> login(@RequestBody @Valid User user) throws Exception {
        userService.selectUserInfo(user);
        String token = createSimpleToken(user);
        log.info("{} Token返回成功", user.getUserName());
        Map<String, Object> data = new HashMap<>();
        data.put("token", token);
        log.info("登陆成功");
        return DataResponse.of(data);
    }
}
