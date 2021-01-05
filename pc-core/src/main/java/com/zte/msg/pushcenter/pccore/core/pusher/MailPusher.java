package com.zte.msg.pushcenter.pccore.core.pusher;

import com.zte.msg.pushcenter.pccore.core.pusher.msg.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
    public void push(Message message) {

    }

//    @Override
//    public void submit(Message message) {
//        MailMessage mailMessage = (MailMessage) message;
//        if (null != mailMessage) {
//            CompletableFuture.supplyAsync(() -> {
//                SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
//                simpleMailMessage.setFrom(AesUtils.decrypt(from));
//                simpleMailMessage.setTo(mailMessage.getTo());
//                simpleMailMessage.setSubject(mailMessage.getSubject());
//                simpleMailMessage.setText(mailMessage.getContent());
//                if (!Objects.isNull(mailMessage.getCc()) && mailMessage.getCc().length > 0) {
//                    simpleMailMessage.setCc(mailMessage.getCc());
//                }
//                mailSender.send(simpleMailMessage);
//                return null;
//            }, pushExecutor).exceptionally(e -> {
//                log.error("Error while send a mail message: {}", e.getMessage());
//                throw new CommonException(ErrorCode.MAIL_PUSH_ERROR);
//            });
//        } else {
//            log.error("Mail push parameter is empty");
//            throw new CommonException(ErrorCode.MAIL_PARAM_EMPTY);
//        }
//    }

}