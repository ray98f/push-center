package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.dto.res.WhiteIpResDTO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author frp
 */
@Mapper
@Repository
public interface WhiteIpMapper {

    /**
     * 获取白名单列表appId列表
     * @return
     */
    List<WhiteIpResDTO> listAppId();

    /**
     * 获取应用对应白名单
     * @param appId
     * @return
     */
    List<String> selectWhiteIp(Long appId);
}
