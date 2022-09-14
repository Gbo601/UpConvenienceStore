package org.wlzhj.ucs_admin.shiro;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: AccountProfile
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 19:35
 * @Description: 登录成功后返回用户信息载体
 */
@Data
public class AccountProfile implements Serializable {
    private Long id;
    private String username;
}
