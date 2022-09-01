package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
        System.out.println(list);
        model.addAttribute("list",list);
        return "adminTable";
    }
}
