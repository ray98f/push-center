package com.zte.msg.pushcenter.msg;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.dto.req.WeChatMessageReqDTO;
import com.zte.msg.pushcenter.utils.UidUtils;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.sql.Timestamp;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/15 10:27
 */
@Data
public class WeChatMessage extends Message {


    /**
     * 公众号appId
     */
    private String weChatAppId;

    /**
     * 公众号secret
     */
    private String appSecret;

    /**
     * 接口配置里的EncodingAESKey值
     */
    private String aesKey;

    /**
     * 公众号配置的token
     */
    private String appToken;

    /**
     * 目标用户微信
     */
    private String targetWeChatId;

    /**
     * 模版id
     */
    private String templateId;

    /**
     * 参数
     */
    private JSONObject var;

    public WeChatMessage build(WeChatMessageReqDTO reqDTO) {
        BeanUtils.copyProperties(reqDTO, this);
        this.setMessageId(UidUtils.getInstance().nextId());
        this.setCreateTime(new Timestamp(System.currentTimeMillis()));
        return this;
    }
}
