package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.UserDao;
import org.wlzhj.ucs_admin.pojo.User;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Controller
public class UserController {
    @Resource
    UserDao usersDao;


    @PostMapping ("addUser")
    public String add(User user){//增加信息
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        user.setJointime(t);
        usersDao.add(user);
        return " ";
    }
    @PostMapping("removeUser")
    public String remove(int id){//删除信息
        usersDao.remove(id);
        return " ";
    }
    @PostMapping("setUser")
    public String showById(User user){//修改信息
        usersDao.set(user);
        return " ";
    }
    @GetMapping("showByIdUser")
    public String showById(int id,Model model){//根据id查询信息
        User user1=usersDao.showById(id);
        model.addAttribute("User",user1);
        System.out.println(user1);
        return " ";
    }

    @GetMapping("showUser")
    public String show(Model model){//获取全部信息
        List<User> list = usersDao.show();
        model.addAttribute("list", list);
        return "userTable";
    }


}
