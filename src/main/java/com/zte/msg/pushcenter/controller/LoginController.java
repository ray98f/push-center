package com.zte.msg.pushcenter.controller;

import com.zte.msg.pushcenter.dto.DataResponse;
import com.zte.msg.pushcenter.dto.SimpleTokenInfo;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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

    @PostMapping("/login")
    @ApiOperation(value = "管理平台登录")
    public DataResponse<Map<String, Object>> login(@RequestBody @Valid SimpleTokenInfo simpleTokenInfo) throws Exception {
        if(Objects.isNull(simpleTokenInfo)){
            log.error("");
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        Map<String, Object> data = new HashMap<>();

        data.put("token", "1");
        return DataResponse.of(data);
    }
}
