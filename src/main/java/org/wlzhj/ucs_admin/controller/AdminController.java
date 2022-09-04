package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.AdminDao;
import org.wlzhj.ucs_admin.pojo.Admin;

import javax.annotation.Resource;
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

    @GetMapping("/showAllAdmin")
    public String showAllAdmin(Model model){
        List<Admin> list = adminDao.getAllAdmin();
        model.addAttribute("list",list);
        return "adminTable";
    }

    @GetMapping("toAddAdminPage")
    public String toAddAdminPage(){
        return "addAdmin";
    }
    @PostMapping("addAdmin")
    public String add(Admin admin){
        adminDao.add(admin);
        return "redirect:/showAllAdmin";
    }

    @GetMapping("toAdminIndex")
    public String toAdminIndex(){
        return "index";
    }
}
