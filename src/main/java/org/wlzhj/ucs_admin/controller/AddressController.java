package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.AddressDao;
import org.wlzhj.ucs_admin.pojo.Address;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ClassName: AddressController
 * @Author: Gbo601
 * @Date: 2022-2022/9/2 21:44
 * @Description: TODO
 */
@Controller
public class AddressController {
    @Resource
    AddressDao addressDao;

    @PostMapping("addAddress")
    public String addAddress(Address address, HttpSession session){
        int userId = (int) session.getAttribute("userId");
        address.setUserId(userId);
        System.out.println(address);
        addressDao.addAddress(address);
        return "redirect:/toUserSettingPage";
    }

    @GetMapping("deleteAddress")
    public String deleteAddress(int id){
        addressDao.deleteAddress(id);
        return "redirect:/toUserSettingPage";
    }

    @GetMapping("setDefaultAddress")
    public String setDefaultAddress(int id,HttpSession session){
        int userId = (int) session.getAttribute("userId");
        addressDao.setDeleteCheck(userId);
        addressDao.setCheckStatus(true,userId,id);
        return "redirect:/toUserSettingPage";
    }

}
