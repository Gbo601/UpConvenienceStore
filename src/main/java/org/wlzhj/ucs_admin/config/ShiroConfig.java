package org.wlzhj.ucs_admin.config;

import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.wlzhj.ucs_admin.shiro.AccountRealm;
import org.wlzhj.ucs_admin.shiro.JwtFilter;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 17:14
 * @Description: Shiro配置
 */
@Configuration
public class ShiroConfig {
    @Autowired
    JwtFilter jwtFilter;

    /**
     * @Describe: 重写SessionManager
     * @Param: [redisSessionDAO]
     * @Return: org.apache.shiro.session.mgt.SessionManager
     * @Author: Gbo601
     * @Date: 2021/10/24 18:40
     */
    @Bean
    public SessionManager sessionManager(RedisSessionDAO redisSessionDAO){
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO);
        return sessionManager;
    }

    /**
     * @Describe: 重写DefaultWebSecurityManager,权限数据和会话信息保存在redis中，实现会话共享
     * @Param: [accountRealm, sessionManager, redisCacheManager]
     * @Return: org.apache.shiro.web.mgt.DefaultWebSecurityManager
     * @Author: Gbo601
     * @Date: 2021/10/24 18:49
     */
    @Bean
    public DefaultWebSecurityManager securityManager(AccountRealm accountRealm, SessionManager sessionManager, RedisCacheManager redisCacheManager){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(accountRealm);
        securityManager.setSessionManager(sessionManager);
        securityManager.setCacheManager(redisCacheManager);

//        关闭shiro自带的session
        DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
        defaultSessionStorageEvaluator.setSessionStorageEnabled(false);

        DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
        subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
        securityManager.setSubjectDAO(subjectDAO);
        return securityManager;
    }

    /**
     * @Describe: 所有的路由经过JwtFilter过滤器
     * @Param: []
     * @Return: org.apache.shiro.spring.web.config.ShiroFilterChainDefinition
     * @Author: Gbo601
     * @Date: 2021/10/24 18:49
     */
    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition(){

        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/**", "jwt"); // 主要通过注解方式校验权限
        chainDefinition.addPathDefinitions(filterMap);
        return chainDefinition;
    }

    /**
     * @Describe: 配置过滤器
     * @Param: [securityManager, shiroFilterChainDefinition]
     * @Return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @Author: Gbo601
     * @Date: 2021/10/24 18:49
     */
    @Bean("shiroFilterFactoryBean")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(SecurityManager securityManager,ShiroFilterChainDefinition shiroFilterChainDefinition){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>();
        filters.put("jwt", jwtFilter);
        shiroFilter.setFilters(filters);

        Map<String, String> filterMap = shiroFilterChainDefinition.getFilterChainMap();
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;
    }

    /**
     * @Describe: 开启注解代理
     * @Param: [securityManager]
     * @Return: org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor
     * @Author: Gbo601
     * @Date: 2021/10/24 17:45
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager){

        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager);
        return authorizationAttributeSourceAdvisor;

    }

    /**
     * @Describe: <br>
     * @Param: []
     * @Return: org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator
     * @Author: Gbo601
     * @Date: 2021/10/24 18:49
     */
    @Bean
    public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator(){

        DefaultAdvisorAutoProxyCreator creator = new DefaultAdvisorAutoProxyCreator();
        return creator;
    }
}
