package com.zte.msg.pushcenter.pccore.core.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.pccore.core.pusher.base.BasePusher;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Config;
import com.zte.msg.pushcenter.pccore.core.pusher.base.Message;
import com.zte.msg.pushcenter.pccore.core.pusher.msg.MailMessage;
import com.zte.msg.pushcenter.pccore.entity.MailInfo;
import com.zte.msg.pushcenter.pccore.entity.Provider;
import com.zte.msg.pushcenter.pccore.enums.ErrorCode;
import com.zte.msg.pushcenter.pccore.enums.PushMethods;
import com.zte.msg.pushcenter.pccore.exception.CommonException;
import com.zte.msg.pushcenter.pccore.service.HistoryService;
import com.zte.msg.pushcenter.pccore.utils.AesUtils;
import com.zte.msg.pushcenter.pcscript.PcScript;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/11 9:34
 */
@Slf4j
@Component
public class MailPusher extends BasePusher {

    @Value("${spring.mail.username}")
    private String from;

    @Resource
    private HistoryService historyService;

    @Override
    public void push(Message message) {
        MailMessage mailMessage = (MailMessage) message;
        CompletableFuture.supplyAsync(() -> {
            log.info("==========submit mail push task==========");
            MailConfig config = (MailConfig) configMap.get(PushMethods.MAIL)
                    .get(mailMessage.getProviderId()).get(mailMessage.getProviderId().intValue());
            JavaMailSenderImpl mailSender = buildMailSender(config);
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(AesUtils.decrypt(from));
            simpleMailMessage.setTo(mailMessage.getTo());
            simpleMailMessage.setSubject(mailMessage.getSubject());
            simpleMailMessage.setText(mailMessage.getContent());
            if (!Objects.isNull(mailMessage.getCc()) && mailMessage.getCc().length > 0) {
                simpleMailMessage.setCc(mailMessage.getCc());
            }
            mailSender.send(simpleMailMessage);
            mailMessage.setTransmitTime(new Timestamp(System.currentTimeMillis()));
            return new PcScript.Res(0, "发送成功");
        }, pushExecutor).exceptionally(e -> {
            log.error("Error while send a mail message: {}", e.getMessage());
            throw new CommonException(ErrorCode.MAIL_PUSH_ERROR);
        }).thenAcceptAsync(o -> persist(mailMessage, o));
    }

    @Override
    protected void persist(Message message, PcScript.Res res) {
        MailMessage mailMessage = (MailMessage) message;
        MailInfo mailInfo = new MailInfo(mailMessage, res);
        historyService.addHistoryMail(mailInfo);
    }

    @Override
    protected void init() {
        List<Provider> providers = providerService.getProviderByType(PushMethods.MAIL.value());
        providers.forEach(this::flushConfig);
        log.info("========== initialize sms config completed : {}  ========== ", providers.size());
    }

    public void flushConfig(List<Provider> providers) {
        flushConfig(providers, false);
    }

    public void flushConfig(List<Provider> providers, boolean remove) {
        providers.forEach(o -> flushConfig(o, remove));
    }

    public void flushConfig(Provider provider) {
        flushConfig(provider, false);
    }

    public void flushConfig(Provider provider, boolean remove) {
        if (!remove) {
            TreeMap<Integer, Config> treeMap = configMap.get(PushMethods.MAIL).get(provider.getId());
            if (Objects.isNull(treeMap)) {
                treeMap = new TreeMap<>();
            }
            treeMap.put(provider.getId().intValue(), JSONObject.parseObject(provider.getConfig(), MailConfig.class));
            configMap.get(PushMethods.MAIL).put(provider.getId(), treeMap);
        }
    }

    @Data
    protected static class MailConfig implements Config {

        private String host;

        private Integer port;

        private String protocol;

        private String username;

        private String password;
    }

    public JavaMailSenderImpl buildMailSender(MailConfig mailConfig) {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        javaMailSender.setProtocol(mailConfig.getProtocol());
        javaMailSender.setHost(mailConfig.getHost());
        javaMailSender.setPort(mailConfig.getPort());
        javaMailSender.setUsername(AesUtils.decrypt(mailConfig.getUsername()));
        javaMailSender.setPassword(AesUtils.decrypt(mailConfig.getPassword()));
        return javaMailSender;
    }

}