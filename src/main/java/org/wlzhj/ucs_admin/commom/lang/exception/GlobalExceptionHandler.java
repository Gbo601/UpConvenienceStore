package org.wlzhj.ucs_admin.commom.lang.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.wlzhj.ucs_admin.commom.lang.lang.Result;

import java.io.IOException;

/**
 * @ClassName: GlobalExceptionHandler
 * @Author: Gbo601
 * @Date: 2021-2021/10/24 20:18
 * @Description: 全局异常处理,设计返回友好简单格式的异常给前端
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @Describe: 捕获shiro异常,比如没有权限,用户登录异常
     * @Param: [e]
     * @Author: Gbo601
     * @Date: 2021/10/24 20:25
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result handle401(ShiroException e){
        return Result.fail("401",e.getMessage(),null);
    }


    /**
     * @Describe: 处理Assert异常
     * @Param: [e]
     * @Author: Gbo601
     * @Date: 2021/10/24 20:27
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = IllegalArgumentException.class)
    public Result handler(IllegalArgumentException e)throws IOException  {

        return Result.fail(e.getMessage(),null);
    }

    /**
     * @Describe: 实体校验异常
     * @Param: [e]
     * @Return: com.gbo601.gbook.common.lang.Result
     * @Author: Gbo601
     * @Date: 2021/10/24 20:35
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public Result handler(MethodArgumentNotValidException e)throws IOException{

        BindingResult bindingResult = e.getBindingResult();
        ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
        return Result.fail(objectError.getDefaultMessage());
    }

    /**
     * @Describe: 捕获其他异常
     * @Param: [e]
     * @Author: Gbo601
     * @Date: 2021/10/24 20:36
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = RuntimeException.class)
    public Result handler(RuntimeException e)throws IOException{

        return Result.fail(e.getMessage());
    }




}
