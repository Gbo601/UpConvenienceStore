package org.wlzhj.ucs_admin.pojo;

import lombok.Data;

/**
 * @ClassName: Address
 * @Author: Gbo601
 * @Date: 2022-2022/9/2 21:42
 * @Description: TODO
 */
@Data
public class Address {
    private int id;
    private int userId;
    private String address;
    private String phone;
    private String name;
    private boolean check;
}
