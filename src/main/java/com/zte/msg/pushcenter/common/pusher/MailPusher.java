package com.zte.msg.pushcenter.common.pusher;

import com.alibaba.fastjson.JSONObject;
import com.zte.msg.pushcenter.enums.ErrorCode;
import com.zte.msg.pushcenter.exception.CommonException;
import com.zte.msg.pushcenter.msg.MailMessage;
import com.zte.msg.pushcenter.msg.Message;
import com.zte.msg.pushcenter.utils.AesUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;
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

    @Resource
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Override
    public CompletableFuture<JSONObject> push(Message message) {

        MailMessage mailMessage = (MailMessage) message;
        return CompletableFuture.supplyAsync(() -> {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom(AesUtils.decrypt(from));
            simpleMailMessage.setTo(mailMessage.getTo());
            simpleMailMessage.setSubject(mailMessage.getSubject());
            simpleMailMessage.setText(mailMessage.getContent());
            if (!Objects.isNull(mailMessage.getCc()) && mailMessage.getCc().length > 0) {
                simpleMailMessage.setCc(mailMessage.getCc());
            }
            mailSender.send(simpleMailMessage);
            return new JSONObject();
        }, executor).exceptionally(e -> {
            log.error("Error while send a sms message: {}", e.getMessage());
            throw new CommonException(ErrorCode.PUSH_ERROR);
        });

    }
}
