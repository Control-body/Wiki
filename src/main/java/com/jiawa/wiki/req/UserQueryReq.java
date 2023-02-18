package com.jiawa.wiki.req;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;

@Data
@AutoConfigurationPackage
@NoArgsConstructor
public class UserQueryReq extends PageReq{
    /**
     * ID
     */
    private Long id;

    /**
     * 登录名
     */
    private String loginName;

    /**
     * 昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;
}
