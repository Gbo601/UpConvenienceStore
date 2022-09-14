package org.wlzhj.ucs_admin.commom.lang.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @ClassName: LoginDto
 * @Author: Gbo601
 * @Date: 2022-2022/9/5 09:52
 * @Description: TODO
 */
@Data
public class LoginDto implements Serializable {
    @NotBlank(message = "账户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
}
