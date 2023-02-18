package com.jiawa.wiki.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginReq {
    @NotNull(message="用户名不能为空")
    private String loginName;
    @NotNull(message = "密码不能为空")
    private String password;
}
