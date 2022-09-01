package org.wlzhj.ucs_admin.commom.lang;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName: Result
 * @Author: Gbo601
 * @Date: 2022-2022/8/31 19:33
 * @Description: 封装结果集
 */
@Data
public class Result implements Serializable {


    private int code;
    private String msg;
    private Object object;


    public static Result succ(int code,String msg,Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setObject(object);
        return result;
    }

    public static Result fail(int code,String msg,Object object){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setObject(object);
        return result;
    }

    public static Result succ(Object object){
        Result result = new Result();
        result.setCode(200);
        result.setMsg("操作成功");
        result.setObject(object);
        return result;
    }

    public static Result fail(int code,String msg){
        Result result = new Result();
        result.setCode(400);
        result.setMsg("操作失败");
        result.setObject(null);
        return result;
    }

}
