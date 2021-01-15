package com.zte.msg.pushcenter.pccore.core;

import com.zte.msg.pushcenter.pccore.core.javac.CodeJavac;
import com.zte.msg.pushcenter.pccore.core.pusher.MailPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.WeChatPusher;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 14:23
 */
@Component
@Slf4j
public class Flusher {

    @Resource
    private SmsPusher smsPusher;

    @Resource
    private MailPusher mailPusher;

    @Resource
    private WeChatPusher weChatPusher;

    @Resource
    private CodeJavac codeJavac;

    public void flush(Provider... providers) {
        flush(false, providers);
    }

    public void flush(boolean remove, Provider... providers) {
        codeJavac.scriptFlush(remove, Arrays.stream(providers)
                .filter(o -> (StringUtils.isNotBlank(o.getScriptTag()) && StringUtils.isNotBlank(o.getScriptContext())))
                .map(o -> new ScriptModel(o.getScriptTag(), o.getScriptContext())).collect(Collectors.toList()));
        flushConfig(Arrays.asList(providers), remove);
    }

    private void flushConfig(List<Provider> providers, boolean remove) {
        Map<Integer, List<Provider>> providerType = providers.stream().collect(Collectors.groupingBy(Provider::getType));
        providerType.keySet().forEach(o -> {
            switch (PushMethods.valueOf(o)) {
                case SMS:
                    smsPusher.flushConfig(providerType.get(o), remove);
                    break;
                case MAIL:
                    mailPusher.flushConfig(providerType.get(o), remove);
                    break;
                case APP:
                    // TODO: 2021/1/13
                case WECHAT:
                    weChatPusher.flushConfig(providerType.get(o), remove);
                    break;
                default:
            }
        });
    }
}
