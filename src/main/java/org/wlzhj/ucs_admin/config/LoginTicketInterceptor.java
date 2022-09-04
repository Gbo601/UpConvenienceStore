package org.wlzhj.ucs_admin.config;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;
import org.wlzhj.ucs_admin.utils.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: LoginTicketInterceptor
 * @Author: Gbo601
 * @Date: 2022-2022/8/31 21:09
 * @Description: TODO
 */
@Slf4j
@Component
 class LoginTicketInterceptor implements HandlerInterceptor {

    @Autowired
    TokenUtil tokenUtil;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {

//        查看请求中是否存在token，如果不存在直接跳转到登陆页面
//        String token = tokenUtil.getToken(request);
//        String token =  request.getSession().getAttribute("admin");
//        if (token == null) {
//            response.sendRedirect("/login");
//            return false;
//        }
        return true;
    }
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,  ModelAndView modelAndView) throws Exception {

    }
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,  Exception ex) throws Exception {
    }


}
