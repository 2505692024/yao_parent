package com.yao.userservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yaoheng
 * @date 2021/1/5 10:08
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "用户名")
    private String nickname;
    @ApiModelProperty(value = "手机号")
    private String mobile;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "验证码")
    private String code;
}
