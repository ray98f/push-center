package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.dto.req.RegisterReqDTO;
import com.zte.msg.pushcenter.pccore.dto.res.RegisterResDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface RegisterMapper {
    void register(RegisterReqDTO reqDTO);

    void modifyRegisterInfo(RegisterReqDTO reqDTO);

    void deleteRegisterInfo(RegisterReqDTO reqDTO);

    RegisterResDTO selectRegisterInfo(RegisterReqDTO reqDTO);

    List<String> selectRegisterInfos(RegisterReqDTO reqDTO);
}
