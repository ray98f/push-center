package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.entity.Dic;
import com.zte.msg.pushcenter.pccore.service.DicService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/19 9:57
 */
@RestController
@Api(tags = "字典")
@RequestMapping("/api/v1/dic")
public class DicController {

    @Resource
    private DicService dicService;

    @GetMapping("/all")
    public DataResponse<Dic> getAllDic() {
        return DataResponse.of(dicService.selectAllDic());
    }
}
