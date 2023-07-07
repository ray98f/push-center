package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.entity.ForeignToken;
import org.mapstruct.Mapper;

@Mapper
public interface ForeignTokenMapper {

    void saveToken(ForeignToken token);

    ForeignToken getToken(String sys);

    void resetToken(ForeignToken token);

    void deleteToken(String sys);
}
