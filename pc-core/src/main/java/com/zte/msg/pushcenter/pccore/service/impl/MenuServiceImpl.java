package com.zte.msg.pushcenter.pccore.service.impl;

import com.zte.msg.pushcenter.pccore.dto.req.MenuReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.MenuResDTO;
import com.zte.msg.pushcenter.pccore.dto.res.SuperMenuResDTO;
import com.zte.msg.pushcenter.pccore.entity.Menu;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.mapper.MenuMapper;
import com.zte.msg.pushcenter.pccore.service.MenuService;
import com.zte.msg.pushcenter.pccore.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/18 10:26
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    public static final int INT_BUTTON = 2;

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<SuperMenuResDTO> listSuper(Integer type) {
        List<SuperMenuResDTO> superMenuResDTOList = menuMapper.listSuperCatalog(type);
        if (null == superMenuResDTOList || superMenuResDTOList.isEmpty()) {
            throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
        }
        if (type == INT_BUTTON) {
            for (SuperMenuResDTO superMenuResDTO : superMenuResDTOList) {
                List<SuperMenuResDTO.MenuInfo> menuInfoList = menuMapper.listSuperMenu(superMenuResDTO.getCatalogId());
                if (null == menuInfoList || menuInfoList.isEmpty()) {
                    throw new CommonException(ErrorCode.RESOURCE_NOT_EXIST);
                }
                superMenuResDTO.setMenuInfo(menuInfoList);
            }
        }
        log.info("上级菜单列表返回成功");
        return superMenuResDTOList;
    }

    @Override
    public void insertMenu(Menu menuList) {
        if (Objects.isNull(menuList)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = menuMapper.insertMenu(menuList, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("菜单新增成功");
        } else {
            throw new CommonException(ErrorCode.INSERT_ERROR);
        }
    }

    @Override
    public List<MenuResDTO> listMenu(MenuReqDTO menuReqDTO) {
        if (Objects.isNull(menuReqDTO)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        List<MenuResDTO> list;
        List<MenuResDTO.MenuInfo> menuInfoList;
        List<MenuResDTO.MenuInfo.ButtonInfo> buttonInfoList;
        list = menuMapper.listCatalog(menuReqDTO);
        if (list.isEmpty()) {
//            menuInfoList = menuMapper.listMenu(null, menuReqDTO);
//            if (menuInfoList.isEmpty()) {
//                buttonInfoList = menuMapper.listButton(null, menuReqDTO);
//                menuInfoList.get(0).setButtonInfo(buttonInfoList);
//            } else {
//                for (MenuResDTO.MenuInfo menuInfo : menuInfoList) {
//                    buttonInfoList = menuMapper.listButton(menuInfo.getMenuId(), menuReqDTO);
//                    menuInfo.setButtonInfo(buttonInfoList);
//                }
//            }
            log.error("根目录无下级");
        } else {
            for (MenuResDTO menuResDTO : list) {
                menuInfoList = menuMapper.listMenu(menuResDTO.getMenuId(), menuReqDTO);
                if (menuInfoList.isEmpty()) {
//                    buttonInfoList = menuMapper.listButton(null, menuReqDTO);
//                    menuInfoList.get(0).setButtonInfo(buttonInfoList);
                    log.error(menuResDTO.getMenuId() + "目录无下级");
                } else {
                    for (MenuResDTO.MenuInfo menuInfo : menuInfoList) {
                        buttonInfoList = menuMapper.listButton(menuInfo.getMenuId(), menuReqDTO);
                        menuInfo.setChildren(buttonInfoList);
                    }
                }
                menuResDTO.setChildren(menuInfoList);
            }
        }
        return list;
    }

    @Override
    public void updateMenu(Menu menuList) {
        if (Objects.isNull(menuList)) {
            throw new CommonException(ErrorCode.PARAM_NULL_ERROR);
        }
        int result = menuMapper.updateMenu(menuList, TokenUtil.getCurrentUserName());
        if (result > 0) {
            log.info("{}菜单修改成功", menuList.getMenuId());
        } else {
            throw new CommonException(ErrorCode.UPDATE_ERROR);
        }
    }

    @Override
    public void deleteMenu(Long id) {
        int result = menuMapper.deleteMenu(id);
        if (result > 0) {
            log.info("{}菜单修改成功", id);
        } else {
            throw new CommonException(ErrorCode.DELETE_ERROR);
        }
    }
}
