package org.wlzhj.ucs_admin.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Indent_Item
 * @Author: Gbo601
 * @Date: 2022-2022/9/3 19:19
 * @Description: TODO
 */
@Data
public class Indent_Item {
    private int id;
    private int orderId;
    private int itemId;
    private String itemName;
    private BigDecimal price;
    private int number;
    private String picUrl;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;
}
