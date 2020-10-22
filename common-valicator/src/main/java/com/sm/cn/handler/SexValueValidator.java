package com.sm.cn.handler;

/**
 * @Auther: tjp
 * @Date: 2020/10/21 21:52
 * @Description:
 */

import com.sm.cn.valicatorannotion.SexValues;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;


public class SexValueValidator implements ConstraintValidator<SexValues,String> {
   //初始化注解
    private List<String> list;
    @Override
    public void initialize(SexValues constraintAnnotation) {
        String[] values = constraintAnnotation.values();
        list = Arrays.asList(values);
    }
//验证 (value 传入的值)
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {

        return list.contains(value);
    }

}