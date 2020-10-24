package com.sm.cn.async;

import com.sm.cn.email.EmailService;
import com.sm.cn.spring.SpringUtils;

import static com.sm.cn.spring.SpringUtils.*;

/**
 * @Auther: tjp
 * @Date: 2020/10/23 22:34
 * @Description:异步任务生产工厂
 */
public class AsynvFactory {
    public  static  Runnable executeEmail(String to ,String message){
        Runnable runnable=new Runnable() {
            @Override
            public void run() {
                SpringUtils.getBean(EmailService.class).sendMail(to,message);
            }
        };
        return runnable;
    }
}