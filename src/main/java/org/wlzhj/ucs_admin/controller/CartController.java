package org.wlzhj.ucs_admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.wlzhj.ucs_admin.dao.CartDao;
import org.wlzhj.ucs_admin.dao.ItemDao;
import org.wlzhj.ucs_admin.pojo.Cart;
import org.wlzhj.ucs_admin.pojo.Item;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: CartController
 * @Author: Gbo601
 * @Date: 2022-2022/9/3 10:21
 * @Description: TODO
 */
@Controller
public class CartController {
    @Resource
    CartDao cartDao;

    @Resource
    ItemDao itemDao;

    @GetMapping("/addCart")
    public String addCart(HttpSession session,int id){
        int userId = (int) session.getAttribute("userId");
        Timestamp t = new Timestamp(new Date().getTime());
        Item item = itemDao.showById(id);
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(item.getId());
        cart.setItemName(item.getItemName());
        cart.setPrice(item.getPrice());
        cart.setNumber(1);
        cart.setPicUrl(item.getPicUrl());
        cart.setAddTime(t);
        cart.setChecked(true);
        cartDao.addCart(cart);
        return "redirect:/toCartPage";
    }

    @GetMapping("/toCartPage")
    public String toCartPage(HttpSession session, Model model){
        int userId = (int) session.getAttribute("userId");
        List<Cart> list =  cartDao.showUserCart(userId);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            sum=sum.add(list.get(i).getPrice());
        }
        model.addAttribute("list",list);
        model.addAttribute("sum",sum);
        return "cart";
    }

    @GetMapping("/deleteCart")
    public String deleteCart(int id){
        cartDao.deleteCart(id);
        return "redirect:/toCartPage";
    }

    @GetMapping("showUserCart")
    public String showUserCart(){

        return "";

    }

}
