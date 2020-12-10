package com.zte.msg.pushcenter.common;

import com.zte.msg.pushcenter.msg.Message;
import org.springframework.stereotype.Component;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/9 9:33
 */
@Component
public class Selector {

    public void select(Message message) {

    }

    public enum Company {
        /**
         *
         */
        ALI(1, "阿里"),

        TENCENT(2, "腾讯"),

        GETUI(3, "个推"),

        JIGUANG(4, "极光推送");
        private Integer type;
        private String name;

        Company(Integer type, String name) {

            this.type = type;
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public enum PushType {
        /**
         *
         */
        APP_PUSH(1, "APP推送"),

        EMAIL(2, "邮件推送"),

        MSM(3, "短信推送"),

        TELE(4, "电话语音")
        ;

        private Integer type;
        private String name;


        PushType(Integer type, String name) {
            this.type = type;
            this.name = name;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
