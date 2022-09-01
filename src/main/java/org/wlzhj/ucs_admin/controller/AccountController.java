package org.wlzhj.ucs_admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.AdminDao;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.Admin;
import org.wlzhj.ucs_admin.pojo.User;
import org.wlzhj.ucs_admin.utils.TokenUtil;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: AdminLoginController
 * @Author: Gbo601
 * @Date: 2022-2022/8/31 18:12
 * @Description: TODO
 */
@Controller
public class AccountController {
    @Resource
    AdminDao adminDao;

    @Resource
    UserDao userDao;
    @Autowired
    TokenUtil tokenUtil;

    @GetMapping("/login")
    public String toLoginPage(){
        return "userLogin";
    }
    @PostMapping("/adminLoginCheck")
    public String adminLoginCheck(String adminName, String adminPassword ,HttpServletResponse response,HttpServletRequest request){
        Admin admin = adminDao.adminLogin(adminName,adminPassword);
        if(admin != null){
            String token = tokenUtil.generateToken(admin);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/templates");
            response.addCookie(cookie);
            request.setAttribute("adminName",admin.getAdminName());
            return "index";
        }else{
            return "adminLogin";
        }
    }


    @PostMapping("/userLoginCheck")
    public String userLoginCheck(String userName, String userPassword ,HttpServletResponse response,HttpServletRequest request){
        User user = userDao.login(userName,userPassword);
        if(user != null){
            String token = tokenUtil.generateToken(user);
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            response.addCookie(cookie);
            return "userIndex";
        }else{
            return "userLogin";
        }
    }

    @PostMapping("/userRegister")
    public String userRegister(User user,HttpServletResponse response,HttpServletRequest request){
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        user.setJointime(t);
        System.out.println(user);
        userDao.add(user);
        return "userLogin";
    }
    @GetMapping("/logout")
    public String logOut(HttpServletResponse response){
        Cookie cookie = new Cookie("token", null);
        response.addCookie(cookie);
        return "adminLogin";
    }
}
