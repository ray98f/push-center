package com.zte.msg.pushcenter.pccore.core.pusher;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.Message;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:35
 */
@Slf4j
@Component
public class AppPusher extends BasePusher {


    @Override
    public void push(Message message) {

    }

    @Override
    protected void persist(Message message, PcScript.Res res) {

    }


}
