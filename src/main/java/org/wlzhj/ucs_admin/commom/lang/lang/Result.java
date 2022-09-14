package org.wlzhj.ucs_admin.commom.lang.lang;

import lombok.Data;

/**
 * @ClassName: Result
 * @Author: Gbo601
 * @Date: 2021/10/24 11:22
 * @Description: 统一结果封装
 */
@Data
public class Result {
//    结果代码，200为正常，非400表示异常
    private String code;
//    结果提示信息
    private String msg;
//    结果数据
    private Object data;

    /**
     * @Describe: 传入结果数据，返回操作成功结果集
     * @Param: [data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 11:44
     */
    public static Result success(Object data){
        Result result = new Result();
        result.setCode("200");
        result.setMsg("操作成功");
        result.setData(data);
        return result;
    }

    /**
     * @Describe: 传入结果提示消息，结果数据，返回操作成功结果集
     * @Param: [msg, data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 11:47
     */
    public static Result success(String msg,Object data){
        Result result = new Result();
        result.setCode("200");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * @Describe: 传入结果代码，结果提示消息，结果数据，返回操作成功结果集
     * @Param: [code, msg, data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 11:59
     */
    public static Result success(String code,String msg,Object data){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * @Describe: 传入结果数据，返回操作失败结果集
     * @Param: [data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 12:02
     */
    public static Result fail(Object data){
        Result result = new Result();
        result.setCode("400");
        result.setMsg("操作失败");
        result.setData(data);
        return result;
    }

    /**
     * @Describe: 传入结果提示消息，结果数据，返回操作失败结果集
     * @Param: [msg, data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 12:04
     */
    public static Result fail(String msg,Object data){
        Result result = new Result();
        result.setCode("400");
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * @Describe: 传入结果代码，结果提示消息，结果数据，返回操作失败结果集
     * @Param: [code, msg, data]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 12:06
     */
    public static Result fail(String code,String msg,Object data){

        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

}
