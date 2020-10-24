package com.sm.cn.async;

import com.sm.cn.spring.SpringUtils;

import javax.xml.ws.soap.Addressing;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static com.sm.cn.spring.SpringUtils.*;

/**
 * @Auther: tjp
 * @Date: 2020/10/23 22:18
 * @Description:   全局统一异步管理器
 */
public class AsyncManager {

//普通的类获取容器中得bean
    private ScheduledThreadPoolExecutor executor=SpringUtils.getBean(ScheduledThreadPoolExecutor.class);
    //默认延迟10s
    private  long delay=10;

private  AsyncManager(){};
private static  AsyncManager asyncManager=new AsyncManager();
public static AsyncManager getInstance(){
    return asyncManager;

    }
    public   void  executeTask(Runnable runnable){
     executor.schedule(runnable,delay,TimeUnit.SECONDS);
    }

    public  void close(){
    if(!executor.isShutdown()){
        executor.shutdown();
    }
    }
}