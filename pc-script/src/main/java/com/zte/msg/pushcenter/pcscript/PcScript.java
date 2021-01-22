package com.zte.msg.pushcenter.pcscript;

import java.util.Map;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/31 9:46
 */
public interface PcScript {

    /**
     * 执行脚本方法入口
     * @param params
     * @return
     */
    Res execute(Map<String, Object> params);

    class Res {

        private Integer code;

        private String message;

        public Res(Integer code, String message) {
            this.code = code;
            this.message = message;
        }

        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }

}
