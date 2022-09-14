package org.wlzhj.ucs_admin.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wlzhj.ucs_admin.commom.lang.lang.Result;
import org.wlzhj.ucs_admin.dao.AdminDao;
import org.wlzhj.ucs_admin.dao.IndentDao;
import org.wlzhj.ucs_admin.dao.ItemDao;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.Admin;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @ClassName: AdminController
 * @Author: Gbo601
 * @Date: 2022-2022/9/1 11:25
 * @Description: TODO
 */
@Controller
public class AdminController {

    @Resource
    AdminDao adminDao;

    @Resource
    ItemDao itemDao;

    @Resource
    UserDao userDao;

    @Resource
    IndentDao indentDao;

    @GetMapping("/showAllAdmin")
    public String showAllAdmin(Model model){
        List<Admin> list = adminDao.getAllAdmin();
        model.addAttribute("list",list);
        return "adminTable";
    }
    @RequiresAuthentication
    @GetMapping("toAddAdminPage")
    public String toAddAdminPage(){
        return "addAdmin";
    }
    @PostMapping("addAdmin")
    public String add(Admin admin){
        adminDao.add(admin);
        return "redirect:/showAllAdmin";
    }

    @RequiresAuthentication
    @GetMapping("toAdminIndex")
    public String toAdminIndex(Model model){
        int itemSum = itemDao.showItemSum();
        int userSum = userDao.showUserSum();
        int noDeliverIndentSum = indentDao.showNoDeliverIndentSum();

        model.addAttribute("itemSum",itemSum);
        model.addAttribute("userSum",userSum);
        model.addAttribute("noDeliverIndentSum",noDeliverIndentSum);

        return "index";
    }
}
