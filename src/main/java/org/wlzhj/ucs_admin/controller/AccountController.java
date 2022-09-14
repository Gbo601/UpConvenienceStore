package org.wlzhj.ucs_admin.controller;

import cn.hutool.core.map.MapUtil;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wlzhj.ucs_admin.commom.lang.dto.LoginDto;
import org.wlzhj.ucs_admin.commom.lang.dto.RegisterDto;
import org.wlzhj.ucs_admin.commom.lang.lang.Result;
import org.wlzhj.ucs_admin.dao.AdminDao;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.Admin;
import org.wlzhj.ucs_admin.pojo.User;
import org.wlzhj.ucs_admin.utils.JwtUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @ClassName: AdminLoginController
 * @Author: Gbo601
 * @Date: 2022-2022/8/31 18:12
 * @Description: TODO
 */
@CrossOrigin
@Controller
public class AccountController {
    @Resource
    AdminDao adminDao;

    @Resource
    UserDao userDao;

    @Autowired
    JwtUtils jwtUtils;



    @GetMapping("/adminLogin")
    public String toAdminLoginPage(){
        return "adminLogin";
    }


    @PostMapping("/adminLoginCheck")
    @ResponseBody
    public Result adminLoginCheck(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response, HttpSession session) {
        Admin admin = adminDao.getAdminByName(loginDto.getUsername());
        if(admin == null){
            return Result.fail("400","无此用户","adminLogin");
        }else if(!admin.getAdminPassword().equals(loginDto.getPassword())){
            return Result.fail("400","密码错误","adminLogin");
        }else{
            String jwt = jwtUtils.generateToken(admin.getId());

            response.setHeader("Authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            session.setAttribute("username",admin.getAdminName());
            session.setAttribute("id",admin.getId());

            return Result.success("200","登录成功！",MapUtil.builder()
                    .put("id",admin.getId())
                    .put("username",admin.getAdminName())
                    .put("token",jwt)
                    .map());
        }
    }

    @GetMapping("/logoutAdmin")
    public String logoutAdmin(){
        SecurityUtils.getSubject().logout();
        return "adminLogin";
    }
    @GetMapping("/login")
    public String toLoginPage(){
        return "userLogin";
    }

    @PostMapping("/userLoginCheck")
    @ResponseBody
    public Result userLoginCheck(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response, HttpSession session){
        User user = userDao.getUserByName(loginDto.getUsername());
        if(user == null){
            return Result.fail("400","无此用户","login");
        }else if(!user.getUserPassword().equals(loginDto.getPassword())){
            return Result.fail("400","密码错误","login");
        }else{
            String jwt = jwtUtils.generateToken(user.getId());

            response.setHeader("Authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "Authorization");
            session.setAttribute("username",user.getUserName());
            session.setAttribute("userId",user.getId());

            return Result.success("200","登录成功！",MapUtil.builder()
                    .put("id",user.getId())
                    .put("username",user.getUserName())
                    .put("token",jwt)
                    .map());
        }
    }

    @GetMapping("/toRegisterPage")
    public String toRegisterPage(){
        return "userRegister";
    }

    @PostMapping("/userRegister")
    @ResponseBody
    public Result userRegister(@Validated @RequestBody RegisterDto registerDto){
        User user1 = userDao.getUserByName(registerDto.getUsername());
        User user2 = userDao.getUserByPhone(registerDto.getPhone());
        User user = new User();
        if(user1 != null){
            return Result.fail("400","该用户名已被注册","login");
        }
        if(user2 != null){
            return Result.fail("400","该手机号已被注册","login");
        }

        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());

        user.setJointime(t);
        user.setUserName(registerDto.getUsername());
        user.setUserPassword(registerDto.getPassword());
        user.setEmail(registerDto.getEmail());
        user.setPhone(registerDto.getPhone());
        user.setMoney(BigDecimal.ZERO);

        userDao.add(user);
        return Result.success("200","注册成功","login");

    }

    @GetMapping("/logoutUser")
    public String logoutUser(){
        SecurityUtils.getSubject().logout();
        return "redirect:/login";
    }


}
