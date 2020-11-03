package com.sm.cn.exception;

import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: tjp
 * @Date: 2020/10/21 22:06
 * @Description:       Controller的切面，对异常统一处理hibernator validator 验证异常
 */
@RestControllerAdvice
public class CrmExceptionController {
    /***
     * 处理hibernator validator 验证异常
     * @param MethodArgumentNotValidException
     * @return
     */
  @ExceptionHandler(MethodArgumentNotValidException.class)
    public AjaxResult validException(MethodArgumentNotValidException e){
      BindingResult bindingResult=e.getBindingResult();
      Map<String,Object> map=new HashMap<>();
      if(bindingResult.hasErrors()){
          List<FieldError> fieldErrors = bindingResult.getFieldErrors();
          fieldErrors.forEach(i->{
              map.put(i.getField(),i.getDefaultMessage());
          });
      }
      return AjaxResult.error(AjaxStatus.VALID_FAILURE,map);
    }
}