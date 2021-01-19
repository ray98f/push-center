package com.zte.msg.pushcenter.pccore.controller;

import com.zte.msg.pushcenter.pccore.dto.DataResponse;
import com.zte.msg.pushcenter.pccore.dto.req.MenuReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.MenuResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SuperMenuResDTO;
import com.zte.msg.pushcenter.pccore.entity.Menu;
import com.zte.msg.pushcenter.pccore.service.MenuService;
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
 * @date 2020/12/28 14:42
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/menu")
@Api(tags = "菜单管理")
@Validated
public class MenuController {

    @Resource
    private MenuService menuService;

    /**
     * 获取上级菜单列表
     *
     * @param type 类型
     * @return List<SuperMenuResDTO>
     */
    @GetMapping
    @ApiOperation(value = "获取上级菜单列表")
    public DataResponse<List<SuperMenuResDTO>> listSuper(@Valid @RequestParam @NotNull(message = "32000006") Integer type) {
        return DataResponse.of(menuService.listSuper(type));
    }

    /**
     * 新增菜单管理
     *
     * @param menuList 新增的菜单信息
     * @return <T>
     */
    @PutMapping
    @ApiOperation(value = "新增菜单管理")
    public <T> DataResponse<T> insertMenu(@Valid @RequestBody Menu menuList) {
        menuService.insertMenu(menuList);
        return DataResponse.success();
    }

    /**
     * 分页查询菜单信息
     *
     * @param menuReqDTO 分页查询信息
     * @return List<MenuResDTO>
     */
    @PostMapping("/list")
    @ApiOperation(value = "分页查询菜单信息")
    public DataResponse<List<MenuResDTO>> listMenu(@Valid @RequestBody MenuReqDTO menuReqDTO) {
        return DataResponse.of(menuService.listMenu(menuReqDTO));
    }

    /**
     * 修改菜单管理
     *
     * @param menuList 菜单管理信息
     * @return <T>
     */
    @PostMapping
    @ApiOperation(value = "修改菜单管理")
    public <T> DataResponse<T> updateMenu(@Valid @RequestBody Menu menuList) {
        menuService.updateMenu(menuList);
        return DataResponse.success();
    }

    /**
     * 删除菜单管理
     *
     * @param id 删除id信息
     * @return <T>
     */
    @DeleteMapping
    @ApiOperation(value = "删除菜单管理")
    public <T> DataResponse<T> deleteMenu(@Valid @RequestParam @NotNull(message = "32000006") Long id) {
        menuService.deleteMenu(id);
        return DataResponse.success();
    }
}