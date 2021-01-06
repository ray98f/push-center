package com.zte.msg.pushcenter.pccore.mapper;

import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.Sms;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * description:
 *
 * @author frp
 * @version 1.0
 * @date 2021/1/6 10:12
 */
@Mapper
@Repository
public interface HistoryMapper {

    List<Sms> listHistorySms();

    List<MailInfo> listHistoryMail(MailHistoryReqDTO mailHistoryReqDTO);
}
