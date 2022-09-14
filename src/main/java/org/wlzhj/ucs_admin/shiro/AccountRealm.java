package org.wlzhj.ucs_admin.shiro;

import cn.hutool.core.bean.BeanUtil;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.wlzhj.ucs_admin.dao.AdminDao;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.Admin;
import org.wlzhj.ucs_admin.pojo.User;
import org.wlzhj.ucs_admin.utils.JwtUtils;

/**
 * @ClassName: AccountRealm
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 19:05
 * @Description: 登录校验,权限校验
 */
@Component
public class AccountRealm extends AuthorizingRealm  {
    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    AdminDao adminDao;

    @Autowired
    UserDao userDao;

    /**
     * @Describe: 让realm支持jwt凭证登录
     * @Param: [token]
     * @Return: boolean
     * @Author: Gbo601
     * @Date: 2021/10/24 19:11
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * @Describe: 重写AuthorizationInfo,权限校验
     * @Param: [principalCollection]
     * @Return: org.apache.shiro.authz.AuthorizationInfo
     * @Author: Gbo601
     * @Date: 2021/10/24 19:07
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        return null;
    }

    /**
     * @Describe: 重写AuthenticationInfo,登录认证校验
     * @Param: [authenticationToken]
     * @Return: org.apache.shiro.authc.AuthenticationInfo
     * @Author: Gbo601
     * @Date: 2021/10/24 19:08
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        JwtToken jwtToken= (JwtToken) token;
        String userId = jwtUtils.getClaimByToken((String) jwtToken.getPrincipal()).getSubject();
        Admin admin = adminDao.getAdminById(Integer.parseInt(userId));

        if(admin == null){
            throw new UnknownAccountException("账户不存在");
        }

        AccountProfile profile = new AccountProfile();
        BeanUtil.copyProperties(admin,profile);


        return new SimpleAuthenticationInfo(profile,jwtToken.getCredentials(),getName());
    }


}
