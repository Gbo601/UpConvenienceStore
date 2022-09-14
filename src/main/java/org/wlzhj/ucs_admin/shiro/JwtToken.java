package org.wlzhj.ucs_admin.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @ClassName: JwtToken
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 19:17
 * @Description: 自定义Jwt,完成shiro的supports方法
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token){
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
