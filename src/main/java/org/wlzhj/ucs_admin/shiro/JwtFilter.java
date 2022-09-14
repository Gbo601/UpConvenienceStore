package org.wlzhj.ucs_admin.shiro;

import cn.hutool.json.JSONUtil;
import io.jsonwebtoken.Claims;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.wlzhj.ucs_admin.commom.lang.lang.Result;
import org.wlzhj.ucs_admin.utils.JwtUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName: JwtFilter
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 19:38
 * @Description: 定义过滤器
 */
@Component
public class JwtFilter extends AuthenticatingFilter {

    @Autowired
    JwtUtils jwtUtils;

    /**
     * @Describe: 生成自定义的JwtToken实现登录
     * @Param: [servletRequest, servletResponse]
     * @Return: org.apache.shiro.authc.AuthenticationToken
     * @Author: Gbo601
     * @Date: 2021/10/24 20:00
     */
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {

//        获取token
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(StringUtils.isEmpty(jwt)){
            return null;
        }
        return new JwtToken(jwt);
    }

    /**
     * @Describe: 拦截校验,当头部无Authorization时直接通过,不需要自动登录;
     * 当带有的时候校验劲jwt的有效性,没问题直接执行executeLogin自动登录.
     * @Param: [servletRequest, servletResponse]
     * @Return: boolean
     * @Author: Gbo601
     * @Date: 2021/10/24 20:02
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String jwt = request.getHeader("Authorization");
        if(org.springframework.util.StringUtils.isEmpty(jwt)){
            return true;
        }else{
            //校验jwt
            Claims claim=jwtUtils.getClaimByToken(jwt);
            if(claim == null|| jwtUtils.isTokenExpired(claim.getExpiration())){
                throw new ExpiredCredentialsException("token已失效，请重新登陆");
            }
            //登陆处理
            return executeLogin(servletRequest,servletResponse);
        }
    }

    /**
     * @Describe: 登录异常时进入,将异常信息封装抛出
     * @Param: [token, e, request, response]
     * @Return: boolean
     * @Author: Gbo601
     * @Date: 2021/10/24 20:03
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        try {
            //处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            Result r = Result.fail(throwable.getMessage());
            String json = JSONUtil.toJsonStr(r);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
        }
        return false;
    }

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        HttpServletResponse httpServletResponse = WebUtils.toHttp(response);
        httpServletResponse.setHeader("Access-control-Allow-Origin", httpServletRequest.getHeader("Origin"));
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
        // 跨域时会首先发送一个OPTIONS请求，这里我们给OPTIONS请求直接返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(org.springframework.http.HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }
}

