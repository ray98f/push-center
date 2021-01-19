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

    List<SuperMenuResDTO> listSuperCatalog(Integer type);

    List<SuperMenuResDTO.MenuInfo> listSuperMenu(Long id);

    int insertMenu(Menu menuList, String doName);

    List<MenuResDTO> listCatalog(MenuReqDTO menuReqDTO);

    List<MenuResDTO.MenuInfo> listMenu(Long catalogId, MenuReqDTO menuReqDTO);

    List<MenuResDTO.MenuInfo.ButtonInfo> listButton(Long menuId, MenuReqDTO menuReqDTO);

    int updateMenu(Menu menuList, String doName);

    int deleteMenu(List<Long> ids);

}
