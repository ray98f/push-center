package com.zte.msg.pushcenter.msg;

import lombok.Data;

/**
 * description: 管理员应先在个推、极光推送、阿里、腾讯相关网站创建相关App，
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/10 15:27
 */
@Data
public class AppPushMessage extends Message {

    private String targetAppId;

}
