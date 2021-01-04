package com.zte.msg.pushcenter.pccore.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * description:
 *
 * @author chentong
 * @version 1.0
 * @date 2020/12/14 14:29
 */
@ApiModel
@Data
public class MailMessageReqDTO {

    /**
     * 收件人地址
     */
    @ApiModelProperty(value = "收件人地址")
    @NotNull(message = "32000020")
    private String to;

    /**
     * 主题
     */
    @ApiModelProperty(value = "主题")
    private String subject;

    /**
     * 邮件内容
     */
    @ApiModelProperty(value = "邮件内容")
    private String content;

    /**
     * 抄送地址
     */
    @ApiModelProperty(value = "抄送地址")
    private String[] cc;
}
