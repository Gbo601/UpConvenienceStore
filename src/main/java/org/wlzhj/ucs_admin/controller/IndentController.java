package org.wlzhj.ucs_admin.controller;

import jdk.internal.org.objectweb.asm.tree.MethodNode;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Indexed;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.wlzhj.ucs_admin.commom.lang.lang.Result;
import org.wlzhj.ucs_admin.dao.*;
import org.wlzhj.ucs_admin.pojo.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class IndentController {
    @Resource
    IndentDao indentDao;

    @Resource
    CartDao cartDao;

    @Resource
    AddressDao addressDao;

    @Resource
    IndentItemDao indentItemDao;

    @Resource
    ItemDao itemDao;

    @Resource
    UserDao userDao;

    @GetMapping("/showAllIndent")
    public String showAllOrder(Model model){
        List<Indent> list = indentDao.show();
        model.addAttribute("list",list);
        return "indentTable";
    }
    @GetMapping("/showByIdIndent")
    public String showById(int id,Model model){
        Indent indent= indentDao.showById(id);
        model.addAttribute("indent",indent);
        return "updateIndent";
    }
    @PostMapping("/setIndent")
    public String setIndent(Indent indent) {
        indentDao.set(indent);
        return "redirect:/showAllIndent";
    }
    @GetMapping("removeIndent")
    public String remove(int id) {
        indentDao.remove(id);
        return "redirect:/showAllIndent";
    }

    @PostMapping("addIndent")
    public String add(@RequestParam("message")String message, HttpSession session,Model model){
        int userId = (int) session.getAttribute("userId");
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        Address address = addressDao.showCheckAddress(userId);
        Indent indent = new Indent();
        String orderSn = String.valueOf(DigestUtils.md5(userId+t.toString()));
        List<Cart> cartList = cartDao.showUserCart(userId);
//        List<Indent_Item> indent_itemList = new  ArrayList<Indent_Item>();

        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < cartList.size(); i++) {
            sum=sum.add(cartList.get(i).getPrice());
        }

        //添加订单
        indent.setUserId(userId);
        indent.setOrderSn(orderSn);
        indent.setOrderStatus(0);
        indent.setPhone(address.getPhone());
        indent.setAddress(address.getAddress());
        indent.setMessage(message);
        indent.setItemPrice(sum);
        indent.setAddTime(t);
        indentDao.add(indent);
        //订单商品关联
        indent = indentDao.showByOrderSn(orderSn);
        Indent_Item indent_item = new Indent_Item();
        for (int i = 0; i < cartList.size(); i++) {
            indent_item.setOrderId(indent.getId());
            indent_item.setItemId(cartList.get(i).getItemId());
            indent_item.setItemName(cartList.get(i).getItemName());
            indent_item.setPrice(cartList.get(i).getPrice());
            indent_item.setNumber(cartList.get(i).getNumber());
            indent_item.setPicUrl(cartList.get(i).getPicUrl());
            indent_item.setAddTime(t);
            //修改库存
            Item item = itemDao.showById(cartList.get(i).getItemId());
            item.setAmount(item.getAmount()-cartList.get(i).getNumber());
            itemDao.setAmount(item.getAmount(),item.getId());
            //插入数据库
            indentItemDao.addIndentItem(indent_item);
        }
        //删除购物车
        cartDao.deleteUserAllCart(userId);
        model.addAttribute("indent",indent);

        return "checkPage";
    }



    @GetMapping("userToCheckPage")
    public String userToCheckPage(int id,Model model){
        Indent indent = indentDao.showById(id);
        model.addAttribute("indent",indent);
        return  "checkPage";
    }

    @GetMapping("toCheck")
    @ResponseBody
    public Result toCheck(int id){
        Indent indent = indentDao.showById(id);
        User user = userDao.showById(indent.getUserId());
        if(user.getMoney().subtract(indent.getItemPrice()).compareTo(BigDecimal.ZERO) == -1){
            return Result.fail("400","余额不足",null);
        }
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        //修改订单支付状态和时间
        indent.setPayTime(t);
        indent.setOrderStatus(1);
        //扣除余额
        user.setMoney(user.getMoney().subtract(indent.getItemPrice()));
        //插入
        indentDao.set(indent);
        userDao.set(user);

        return Result.success("200","付款成功","toUserSettingPage");
    }

    @GetMapping("cancelIndent")
    @ResponseBody
    public Result cancelIndent(Integer id, HttpSession session){
        Indent indent = indentDao.showById(id);
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        if(indent.getOrderStatus() == 0){
            indent.setOrderStatus(4);
            indent.setRefundTime(t);
        }else{
            int userId = (int) session.getAttribute("userId");
            //金额退还
            User user = userDao.showById(userId);
            user.setMoney(user.getMoney().add(indent.getItemPrice()));
            userDao.set(user);
            indent.setOrderStatus(4);
            indent.setRefundTime(t);
        }
        indentDao.set(indent);
        return Result.success("200","取消成功","toUserSettingPage");
    }
    @GetMapping("confirmIndent")
    @ResponseBody
    public Result confirmIndent(int id){
        Indent indent = indentDao.showById(id);
        Date date = new Date();
        Timestamp t = new Timestamp(date.getTime());
        indent.setOrderStatus(3);
        indent.setRefundTime(t);
        indentDao.set(indent);
        return Result.success("200","收货成功","toUserSettingPage");
    }


    @GetMapping("toIndentPage")
    public String toIndentPage(HttpSession session,Model model){
        int userId = (int) session.getAttribute("userId");
        List<Cart> list = cartDao.showUserCart(userId);
        Address address = addressDao.showCheckAddress(userId);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < list.size(); i++) {
            sum=sum.add(list.get(i).getPrice());
        }
        model.addAttribute("list",list);
        model.addAttribute("Address",address);
        model.addAttribute("sum",sum);

        return "IntentForm";
    }

    @GetMapping("toNoDeliverIndentTable")
    public String tpNoDeliverIndentTable(Model model){
        List<Indent> list = indentDao.showNoDeliverIndent();
        model.addAttribute("list",list);
        return "noDeliverIndentTable";
    }

    @GetMapping("setHasDeliver")
    public String setHasDeliver(int id){
        indentDao.setHasDeliver(id);
        return "redirect:/toNoDeliverIndentTable";
    }

    @GetMapping("orderDetails")
    public String orderDetails(int orderId,Model model){
        List<Indent_Item> indent_item = indentItemDao.getIndentItem(orderId);
        BigDecimal sum = new BigDecimal(0);
        for (int i = 0; i < indent_item.size(); i++) {
            sum=sum.add(indent_item.get(i).getPrice());
        }
        model.addAttribute("list",indent_item);
        model.addAttribute("sum",sum);
        return "IntentDetail";
    }
}
