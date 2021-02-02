package com.zte.msg.pushcenter.pccore.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.zte.msg.pushcenter.pccore.dto.req.ApplicationHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.MailHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.SmsHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.dto.req.WechatHistoryReqDTO;
import com.zte.msg.pushcenter.pccore.entity.ApplicationInfo;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.SmsInfo;
import com.zte.msg.pushcenter.pccore.entity.WeChatInfo;
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

    /**
     * 查询短信历史记录
     *
     * @param page
     * @param smsHistoryReqDTO
     * @return
     */
    Page<SmsInfo> listHistorySms(Page<SmsInfo> page, SmsHistoryReqDTO smsHistoryReqDTO);

    /**
     * 查询邮件历史记录
     *
     * @param page
     * @param mailHistoryReqDTO
     * @return
     */
    Page<MailInfo> listHistoryMail(Page<MailInfo> page, MailHistoryReqDTO mailHistoryReqDTO);

    /**
     * 查询微信推送历史记录
     *
     * @param page
     * @param wechatHistoryReqDTO
     * @return
     */
    Page<WeChatInfo> listHistoryWechat(Page<WeChatInfo> page, WechatHistoryReqDTO wechatHistoryReqDTO);

    /**
     * 查询app推送历史记录
     *
     * @param page
     * @param applicationHistoryReqDTO
     * @return
     */
    Page<ApplicationInfo> listHistoryApplication(Page<ApplicationInfo> page, ApplicationHistoryReqDTO applicationHistoryReqDTO);

}
