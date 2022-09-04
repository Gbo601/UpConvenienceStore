package org.wlzhj.ucs_admin.pojo;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @ClassName: Order
 * @Author: Gbo601
 * @Date: 2022-2022/9/2 20:51
 * @Description: TODO
 */
@Data
public class Indent {
    private int id;
    private int userId;
    private String orderSn;
    private int orderStatus;
    private String phone;
    private String address;
    private String message;
    private BigDecimal itemPrice;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date payTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date addTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date refundTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date confirmTime;


}

