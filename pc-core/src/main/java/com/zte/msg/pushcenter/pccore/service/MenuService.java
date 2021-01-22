package com.zte.msg.pushcenter.pccore.service;

import com.zte.msg.pushcenter.pccore.dto.req.MenuReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.MenuResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SuperMenuResDTO;
import com.zte.msg.pushcenter.pccore.entity.Menu;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/18 17:03
 */
public interface MenuService {

    /**
     * 获取上层菜单列表
     * @param type
     * @return
     */
    List<SuperMenuResDTO> listSuper(Integer type);

    /**
     * 登录动态菜单列表获取
     * @return
     */
    List<MenuResDTO> listLoginMenu();

    /**
     * 新增菜单
     * @param menuList
     */
    void insertMenu(Menu menuList);

    /**
     * 获取菜单列表（查询）
     * @param menuReqDTO
     * @return
     */
    List<MenuResDTO> listMenu(MenuReqDTO menuReqDTO);

    /**
     * 修改菜单
     * @param menuList
     */
    void updateMenu(Menu menuList);

    /**
     * 删除菜单
     * @param ids
     */
    void deleteMenu(List<Long> ids);

    /**
     * 根据权限获取菜单列表
     * @param roleIds
     * @return
     */
    List<MenuResDTO> listMenu(List<Long> roleIds);
}
