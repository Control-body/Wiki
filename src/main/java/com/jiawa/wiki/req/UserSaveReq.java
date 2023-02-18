package com.jiawa.wiki.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveReq {
    /**
     * ID
     */

    private Long id;

    /**
     * 登录名
     */
    @NotNull(message = "[用户名]不能为空")
    private String loginName;

    /**
     * 昵称
     */
    @NotNull(message = "[昵称]不能为空")
    private String name;

    /**
     * 密码
     */
    @NotNull(message = "[密码]不能为空")
    @Length(min = 6,max = 20 ,message = "[密码]6~20位")
    private String password;
}
