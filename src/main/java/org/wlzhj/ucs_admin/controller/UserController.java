package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.AddressDao;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.Address;
import org.wlzhj.ucs_admin.pojo.User;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserDao usersDao;

    @Resource
    AddressDao addressDao;

    @PostMapping ("addUser")
    public String add(User user){//增加信息
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        user.setJointime(t);
        usersDao.add(user);
        return " ";
    }
    @GetMapping("removeUser")
    public String remove(int id){//删除信息
        usersDao.remove(id);
        return "redirect:/showUser";
    }

    @PostMapping("/setUser")
    public String updateUser(User user){//修改信息
        usersDao.set(user);
        return "redirect:/showUser";
    }
    @GetMapping("showByIdUser")
    public String showById(int id,Model model){//根据id查询信息
        User user=usersDao.showById(id);
        model.addAttribute("user",user);
        return "updateUser";
    }

    @GetMapping("showUser")
    public String show(Model model){//获取全部信息
        List<User> list = usersDao.show();
        model.addAttribute("list", list);
        return "userTable";
    }

    @GetMapping("toUserSettingPage")
    public String toUserSettingPage(HttpSession session,Model model){
        int userId = (Integer) session.getAttribute("userId");
        User user = usersDao.showById(userId);
        List<Address> address = addressDao.showPersonalAllAddress(userId);
        model.addAttribute("user",user);
        model.addAttribute("address",address);
        return "userSetting";
    }
    @GetMapping("toUserIndex")
    public String toUserIndex(){
        return "userIndex";
    }

    @PostMapping("userSetPersonal")
    public String userSetPersonal(User user,HttpSession session){
        user.setId((Integer) session.getAttribute("userId"));
        System.out.println(user);
        usersDao.userSetPersonal(user);
        return "redirect:/toUserSettingPage";
    }
}
