package com.sm.cn.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Auther: tjp
 * @Date: 2020/10/23 22:22
 * @Description:  容器已启动 自动执行该方法
 */
@Component
public class SpringUtils  implements ApplicationContextAware {
    private  static  ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext =applicationContext;
    }

    public  static  <T> T getBean(Class<T> tClass){

        return applicationContext.getBean(tClass);
    }
}