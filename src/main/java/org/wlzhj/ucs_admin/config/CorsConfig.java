package org.wlzhj.ucs_admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName: CorsConfig
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 20:42
 * @Description: 跨域处理
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
    //    registry是整个拦截器的注册中心
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginTicketInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/adminLogin").excludePathPatterns("/adminLoginCheck")
                .excludePathPatterns("/login").excludePathPatterns("/userLoginCheck").excludePathPatterns("/userRegister")
                .excludePathPatterns("/logoutAdmin").excludePathPatterns("/logoutUser")
                .excludePathPatterns("")
//                .excludePathPatterns("/showUser")
//                .excludePathPatterns("userTable")
//                .excludePathPatterns("/showAllAdmin")
//                .excludePathPatterns("adminTable")
//                .excludePathPatterns("/showAllItem").excludePathPatterns("/removeItem")
//                .excludePathPatterns("/showByIdItem")
//                .excludePathPatterns("/setItem").excludePathPatterns("updateItem")
                .excludePathPatterns("/css/**","/icons/**","/images/**","/js/**","/scss/**","/vendor/**");;
    }

    @Bean
    public LoginTicketInterceptor authenticationInterceptor() {
        return new LoginTicketInterceptor();
    }
}
