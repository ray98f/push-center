package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zte.msg.pushcenter.pccore.dto.req.MenuReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.MenuResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SuperMenuResDTO;
import com.zte.msg.pushcenter.pccore.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 根据类型获取上层目录列表
     * @param type 类型
     * @return List<SuperMenuResDTO>
     */
    List<SuperMenuResDTO> listSuperCatalog(Integer type);

    /**
     * 搜索目录下的菜单列表（上层菜单）
     * @param id 目录id
     * @return List<SuperMenuResDTO.MenuInfo>
     */
    List<SuperMenuResDTO.MenuInfo> listSuperMenu(Long id);

    /**
     * 新增菜单
     * @param menuList 新增菜单信息
     * @param doName 新增人
     * @return int
     */
    int insertMenu(Menu menuList, String doName);

    /**
     * 获取目录列表
     * @param menuReqDTO 目录列表查询条件
     * @param menuIds 权限内的菜单ids
     * @return List<MenuResDTO>
     */
    List<MenuResDTO> listCatalog(MenuReqDTO menuReqDTO, List<Long> menuIds);

    /**
     * 获取菜单列表
     * @param catalogId 目录ids
     * @param menuReqDTO 目录列表查询条件
     * @param menuIds 权限内的菜单ids
     * @return List<MenuResDTO.MenuInfo>
     */
    List<MenuResDTO.MenuInfo> listMenu(Long catalogId, MenuReqDTO menuReqDTO, List<Long> menuIds);

    /**
     * 获取按钮列表
     * @param menuId 菜单id
     * @param menuReqDTO 菜单请求值
     * @param menuIds 权限内的菜单ids
     * @return List<MenuResDTO.MenuInfo.ButtonInfo>
     */
    List<MenuResDTO.MenuInfo.ButtonInfo> listButton(Long menuId, MenuReqDTO menuReqDTO, List<Long> menuIds);

    /**
     * 获取按钮权限标识
     * @param menuId 菜单id
     * @param menuIds 权限内的菜单ids
     * @return String
     */
    String listButtonRoleIdentify(Long menuId, List<Long> menuIds);

    /**
     * 修改菜单信息
     * @param menuList 修改的菜单信息
     * @param doName 修改人名称
     * @return int
     */
    int updateMenu(Menu menuList, String doName);

    /**
     * 删除菜单
     * @param ids 菜单ids
     * @return int
     */
    int deleteMenu(List<Long> ids);

}
