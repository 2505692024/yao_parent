package com.yao.servicebase.exceptionhandler;

import com.yao.commonutils.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author yaoheng
 * @date 2020/12/4 9:45
 */
@ControllerAdvice
@Slf4j
public class EduExceptionHandler {
    /**
     * 全局异常处理
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.error().message("执行失败");
    }
    /**
     * 特定异常处理
     * @return 错误信息
     */
    @ExceptionHandler(ArithmeticException.class)
    @ResponseBody
    public Result error(ArithmeticException a) {
        a.printStackTrace();
        return Result.error().message("运算异常");
    }
    /**
     * 自定义异常
     * @param e 异常
     * @return 错误信息
     */
    @ExceptionHandler(EduException.class)
    @ResponseBody
    public Result error(EduException e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return Result.error().code(e.getCode()).message(e.getMsg());
    }
}
