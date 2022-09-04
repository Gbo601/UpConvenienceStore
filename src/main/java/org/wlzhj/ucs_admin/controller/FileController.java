package org.wlzhj.ucs_admin.controller;


import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.UUID;

/**
 * @ClassName: FileController
 * @Author: Gbo601
 * @Date: 2022-2022/9/1 22:37
 * @Description: TODO
 */

@RestController
@RequestMapping("/upload")
public class FileController {

    @PostMapping
    public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws Exception {
        // file:上传文件
        // 获取到 images 的具体路径
        // String realPath = request.getRealPath("images");
        String realPath = "D:\\Study\\GDSX\\UpConvenienceStore\\ucs_admin\\src\\main\\resources\\templates\\images\\pic";
        System.out.println("上传的文件地址是：" + realPath);
        // 服务器中对应的位置
        // 产生唯一的文件名称

        String fileName = UUID.randomUUID().toString();
        // 获取到文件后缀
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File src = new File(realPath, fileName + fileType);
        // 将file文件传递到src去
        file.transferTo(src);
        return fileName + fileType;
    }


}
