package com.sm.cn.exception;

import com.sm.cn.http.AjaxResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Auther: tjp
 * @Date: 2020/10/28 17:56
 * @Description:
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


@ExceptionHandler(JwtAuthorizationException.class)
public AjaxResult tokenException(JwtAuthorizationException e){
    return AjaxResult.success(e.getAjaxStatus());
}
}