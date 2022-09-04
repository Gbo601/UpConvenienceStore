package org.wlzhj.ucs_admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.wlzhj.ucs_admin.dao.ItemDao;
import org.wlzhj.ucs_admin.pojo.Item;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
public class ItemController {

    @Resource
    ItemDao itemDao;


    @GetMapping("/toAddItemPage")
    public String toAddPage(){
        return "addItem";
    }

    @PostMapping("addItem")
    public String add(@RequestParam("file") MultipartFile file,Item item) throws IOException {
        if(!file.isEmpty()){
            // file:上传文件
            // 获取到 images 的具体路径
            // String realPath = request.getRealPath("images");
            String realPath = "D:\\Study\\GDSX\\UpConvenienceStore\\ucs_admin\\src\\main\\resources\\templates\\images\\pic";
            // 服务器中对应的位置
            // 产生唯一的文件名称
            String fileName = UUID.randomUUID().toString();
            // 获取到文件后缀
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File src = new File(realPath, fileName + fileType);
            // 将file文件传递到src去
            file.transferTo(src);
            item.setPicUrl(fileName + fileType);
        }
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
    @GetMapping("showByIdItemDetail")
    public String showByIdItemDetail(int id,Model model){
        Item item= itemDao.showById(id);
        model.addAttribute("item",item);
        return "itemDetail";
    }
    @PostMapping("/setItem")
    public String setItem(@RequestParam("file") MultipartFile file,Item item) throws Exception {
        if(!file.isEmpty()){
            // file:上传文件
            // 获取到 images 的具体路径
            // String realPath = request.getRealPath("images");
            String realPath = "D:\\Study\\GDSX\\UpConvenienceStore\\ucs_admin\\src\\main\\resources\\templates\\images\\pic";
            // 服务器中对应的位置
            // 产生唯一的文件名称
            String fileName = UUID.randomUUID().toString();
            // 获取到文件后缀
            String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            File src = new File(realPath, fileName + fileType);
            // 将file文件传递到src去
            file.transferTo(src);

            item.setPicUrl(fileName + fileType);
            itemDao.sethavePic(item);
        }else{
            itemDao.setNoPic(item);
        }
        return "redirect:/showAllItem";
    }
    @GetMapping("showCategory")
    public String showCategory(String category,Model model){
        List<Item> list = itemDao.showCategory(category);
        model.addAttribute("itemList",list);
        return "itemCategory";
    }

}
