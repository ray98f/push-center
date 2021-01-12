package com.zte.msg.pushcenter.pccore.core;

import com.zte.msg.pushcenter.pccore.core.javac.CodeJavac;
import com.zte.msg.pushcenter.pccore.core.pusher.MailPusher;
import com.zte.msg.pushcenter.pccore.core.pusher.SmsPusher;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.model.ScriptModel;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2021/1/12 14:23
 */
@Component
public class Flusher {

    @Resource
    private SmsPusher smsPusher;

    @Resource
    private MailPusher mailPusher;

    @Resource
    private CodeJavac codeJavac;

    public void flush(Provider provider) {
        flush(provider, false);
    }

    public void flush(Provider provider, boolean remove) {
        codeJavac.scriptFlush(new ScriptModel(provider.getScriptTag(), provider.getScriptContext()), remove);
        flushConfig(provider, remove);
    }

    public void flush(List<Provider> providers, boolean remove) {
        List<ScriptModel> scriptModels = new ArrayList<>(providers.size());
        providers.forEach(o -> {
            ScriptModel scriptModel = new ScriptModel(o.getScriptTag(), o.getScriptContext());
            scriptModels.add(scriptModel);
        });
        codeJavac.scriptFlush(scriptModels, remove);

        providers.forEach(o -> flushConfig(o, remove));
    }

    private void flushConfig(Provider provider, boolean remove) {
        switch (PushMethods.valueOf(provider.getType())) {
            case UNKNOWN:
                break;
            case APP:
                // TODO: 2021/1/12
                break;
            case MAIL:
                mailPusher.flushConfig(provider, remove);
                break;
            case WECHAT:
                // TODO: 2021/1/12
                break;
            case SMS:
                smsPusher.flushConfig(provider, remove);
                break;
            default:
        }
    }

    public void configFlush(List<Provider> providers) {
        flush(providers, false);
    }
}
