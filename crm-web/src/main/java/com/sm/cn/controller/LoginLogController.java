package com.sm.cn.controller;

import com.alibaba.druid.sql.visitor.functions.Now;
import com.sm.cn.async.AsyncManager;
import com.sm.cn.async.AsynvFactory;
import com.sm.cn.entity.SysLoginLog;
import com.sm.cn.entity.SysUser;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import com.sm.cn.mytoken.TokenService;
import com.sm.cn.service.ISysLoginLogService;
import com.sm.cn.service.ISysUserService;
import com.sm.cn.useragent.ServletUtils;
import com.sun.media.jfxmedia.logging.Logger;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.log4j.Log4j;
import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * @Auther: tjp
 * @Date: 2020/10/26 20:55
 * @Description: 登录日志控制器
 *   User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.45 Safari/537.36
 *     User-Agent请求头  可以获取到 是电脑登录还是安卓还是IOS, 用第三方库来解析User-Agent获取需要的信息
 */
@RestController
public class LoginLogController {
@Resource
    private ISysLoginLogService sysLoginLogService;

@Autowired
    ISysUserService iSysUserService;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     *  异步任务管理器调用 生产工厂执行 异步日志记录功能
     * @param userName
     * @param password
     * @return
     */
    @GetMapping("log")
public AjaxResult doLoginLog(String userName, String password ){

        if(StringUtils.isEmpty(userName)) {

       AsyncManager.getInstance().executeTask(AsynvFactory.executeLoginLog("1",AjaxStatus.USERNAME_NOT_EMPTY.getMessage(),userName));
       return AjaxResult.error(AjaxStatus.USERNAME_NOT_EMPTY);
   }
   if(StringUtils.isEmpty(password)){
       AsyncManager.getInstance().executeTask(AsynvFactory.executeLoginLog("1",AjaxStatus.PASSWORD_NOT_EMPTY.getMessage(),userName));
       return AjaxResult.error(AjaxStatus.PASSWORD_NOT_EMPTY);
   }

    SysUser byUserName = iSysUserService.findByUserName(userName);
   if(Objects.isNull(byUserName)){
       AsyncManager.getInstance().executeTask(AsynvFactory.executeLoginLog("1",AjaxStatus.USERNAME_NOT_SURE.getMessage(),userName));
       return AjaxResult.error(AjaxStatus.USERNAME_NOT_SURE);
   }

    boolean matches = bCryptPasswordEncoder.matches(password,byUserName.getPassword());
    if(!matches){
        AsyncManager.getInstance().executeTask(AsynvFactory.executeLoginLog("1",AjaxStatus.USERNAME_NOT_SURE.getMessage(),userName));
        return AjaxResult.error(AjaxStatus.PASSWORD_NOT_SURE);
    }

    AsyncManager.getInstance().executeTask(AsynvFactory.executeLoginLog("0",AjaxStatus.LOGIN_SUCCESS.getMessage(),userName));

    return  AjaxResult.success(byUserName);


}


}