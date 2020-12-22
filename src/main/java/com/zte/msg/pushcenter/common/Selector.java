package com.zte.msg.pushcenter.common;

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

    public enum Company {
        /**
         *
         */
        ALI(1, "阿里"),

        TENCENT(2, "腾讯"),

        GETUI(3, "个推"),

        JIGUANG(4, "极光推送"),

        JUHE(5, "聚合"),

        UNKNOWN(6, "未知");
        private Integer type;
        private String name;

        Company(Integer type, String name) {

            this.type = type;
            this.name = name;
        }

        public static Company valueOf(Integer type) {
            for (Company company : Company.values()) {
                if (company.type.equals(type)) {
                    return company;
                }
            }
            return UNKNOWN;
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

        SMS(3, "短信推送"),

        TELE(4, "微信公众号推送"),

        UNKNOWN(-1, "未知");

        private Integer type;
        private String name;

        public static PushType valueOf(Integer type) {
            for (PushType pushType : PushType.values()) {
                if (pushType.type.equals(type)) {
                    return pushType;
                }
            }
            return UNKNOWN;
        }

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
