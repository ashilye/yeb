package com.maple.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户登录实体对象
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value = "AdminLogin 对象",description = "")
public class AdminLoginParam {
    @ApiModelProperty(value = "用户名",required = true) //必填
    private String username;
    @ApiModelProperty(value = "密码",required = true) //必填
    private String password;

}
