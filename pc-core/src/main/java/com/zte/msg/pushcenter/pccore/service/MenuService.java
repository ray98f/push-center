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

    List<SuperMenuResDTO> listSuper(Integer type);

    void insertMenu(Menu menuList);

    List<MenuResDTO> listMenu(MenuReqDTO menuReqDTO);

    void updateMenu(Menu menuList);

    void deleteMenu(List<Long> ids);
}
