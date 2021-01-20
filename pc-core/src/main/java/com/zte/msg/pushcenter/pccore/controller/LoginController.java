package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.res.MenuResDTO;
import com.zte.msg.pushcenter.pccore.entity.User;
import com.zte.msg.pushcenter.pccore.service.MenuService;
import com.zte.msg.pushcenter.pccore.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.zte.msg.pushcenter.pccore.utils.TokenUtil.createSimpleToken;


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

    @Resource
    private MenuService menuService;

    /**
     * 管理平台登录
     * @param user 用户信息
     * @return DataResponse
     * @throws Exception Comm
     */
    @PostMapping("/login")
    @ApiOperation(value = "管理平台登录")
    public DataResponse<Map<String, Object>> login(@RequestBody @Valid User user) throws Exception {
        User userInfo = userService.selectUserInfo(user);
        String token = createSimpleToken(userInfo);
        log.info("{} Token返回成功", userInfo.getUserName());
        List<MenuResDTO> menuResDTOList = menuService.listMenu(userInfo.getRoleId());
        Map<String, Object> data = new HashMap<>(16);
        data.put("token", token);
        data.put("menuInfo", menuResDTOList);
        log.info("登陆成功");
        return DataResponse.of(data);
    }
}
