package org.wlzhj.ucs_admin.utils;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.shiro.SecurityUtils;
import org.wlzhj.ucs_admin.shiro.AccountProfile;

/**
 * @ClassName: ShiroUtils
 * @Author: Gbo601
 * @Date: 2021-2021/10/25 19:17
 * @Description: Shiro工具类
 */
public class ShiroUtils {
    public static AccountProfile getProfile(){
        JSONObject jsonObject = JSONUtil.parseObj(SecurityUtils.getSubject().getPrincipal());
        return JSONUtil.toBean(jsonObject,AccountProfile.class);
    }
}
