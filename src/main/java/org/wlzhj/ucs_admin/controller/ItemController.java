package org.wlzhj.ucs_admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.wlzhj.ucs_admin.dao.ItemDao;
import org.wlzhj.ucs_admin.pojo.Item;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ItemController {

    @Resource
    ItemDao itemDao;


    @GetMapping("/toAddItemPage")
    public String toAddPage(){
        return "addItem";
    }

    @PostMapping("addItem")
    public String add(Item item){
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        item.setAddTime(t);
        itemDao.add(item);
        return "redirect:/showAllItem";
    }

    @GetMapping("showAllItem")
    public String showAllItem(Model model){
        List<Item> list = itemDao.show();
        model.addAttribute("list",list);
        return "itemTable";
    }

    @GetMapping("removeItem")
    public String remove(int id) {
        itemDao.remove(id);
        return "redirect:/showAllItem";
    }
    @GetMapping("showByIdItem")
    public String showById(int id,Model model){
        Item item= itemDao.showById(id);
        model.addAttribute("item",item);
        return "updateItem";
    }

    @PostMapping("/setItem")
    public String setItem(Item item){
        System.out.println(item);
        itemDao.set(item);
        return "redirect:/showAllItem";
    }
}
