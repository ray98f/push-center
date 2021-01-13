package com.zte.msg.pushcenter.pccore.model;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.SmsMessage;
import com.zte.msg.pushcenter.pccore.service.AppService;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.Data;

import javax.annotation.Resource;
import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 18:07
 */
@Data
public class SmsInfoModel {

    private Long appId;

    private String appName;

    private String phoneNum;

    private Timestamp transmitTime;

    private String content;

    private String providerName;

    private Long templateId;

    private Integer result;

    private Integer failCode;

    private String failReason;

    private Timestamp resultTime;

    @Resource
    private AppService appService;

    public SmsInfoModel(SmsMessage message, PcScript.Res res) {
        this.appId = message.getAppId();

        this.appName = message.getAppName();

        this.phoneNum = message.getPhoneNum()[message.getIndex()];

        this.transmitTime = message.getTransmitTime();

        this.content = String.format(message.getContent()
                .replaceAll("#.*?#", "%s")
                .replaceAll("\\{.*?}", "%s"), message.getVars().values().toArray());

        this.providerName = message.getProviderName();

        this.templateId = message.getTemplateId();

        this.failCode = res.getCode();

        this.failReason = res.getMessage();

        // TODO: 2021/1/11
//        this.resultTime =

        if (res.getCode() == 0) {
            this.result = 0;
        } else {
            this.result = 1;
        }
    }

}
