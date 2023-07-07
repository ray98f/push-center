package com.zte.msg.pushcenter.pccore.dto.res;

import com.zte.msg.pushcenter.pccore.validation.RegisterUser;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@ApiModel
public class RegisterResDTO {

    @ApiModelProperty(value = "系统编码")
    private String sysCode;

    @ApiModelProperty(value = "注册ID")
    private String cid;

    @ApiModelProperty(value = "注册ID")
    private List<String> cids;

    @ApiModelProperty(value = "用户ID")
    private String userId;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "手机型号")
    private String mobileVersion;

    @ApiModelProperty(value = "创建人ID")
    private String createBy;

    @ApiModelProperty(value = "创建时间")
    private String createAt;

    @ApiModelProperty(value = "更新人ID")
    private String updateBy;

    @ApiModelProperty(value = "更新时间")
    private String updateAt;

    @ApiModelProperty(value = "操作人ID")
    private String personNo;
}
