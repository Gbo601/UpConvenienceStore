package org.wlzhj.ucs_admin.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Cart
 * @Author: Gbo601
 * @Date: 2022-2022/9/3 10:13
 * @Description: TODO
 */
@Data
public class Cart {
    private int id;
    private int userId;
    private int itemId;
    private String itemName;
    private BigDecimal price;
    private int number;
    private String picUrl;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;
    private boolean checked;
}
