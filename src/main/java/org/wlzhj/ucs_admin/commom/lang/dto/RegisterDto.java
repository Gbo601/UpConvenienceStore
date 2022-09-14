package org.wlzhj.ucs_admin.commom.lang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @ClassName: RegisterDto
 * @Author: Gbo601
 * @Date: 2022-2022/9/6 21:18
 * @Description: TODO
 */
@Data
public class RegisterDto {
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    @NotBlank(message = "邮箱不能为空")
    private String email;
    @NotBlank(message = "手机号不能为空")
    private String phone;
}
