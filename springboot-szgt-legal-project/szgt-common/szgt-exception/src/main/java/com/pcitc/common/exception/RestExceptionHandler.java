package com.pcitc.common.exception;


import com.pcitc.common.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler
    @ResponseBody
    public Result handleResourceNotFoundException(Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            //自定义异常
            return Result.fail(baseException.getCode(), e.getMessage());
        }
        //未知异常
        log.error("未知异常={}",e);
        return Result.fail(5008, e.toString());
    }

}
