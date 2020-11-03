package com.sm.cn.exception;

import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import org.springframework.stereotype.Component;

/**
 * @Auther: tjp
 * @Date: 2020/10/25 22:45
 * @Description: 自定义JWT异常,如果token不对 就抛出
 */

public class JwtAuthorizationException  extends  RuntimeException{
    private AjaxStatus ajaxStatus;

    public AjaxStatus getAjaxStatus() {
        return ajaxStatus;
    }

    public void setAjaxStatus(AjaxStatus ajaxStatus) {
        this.ajaxStatus = ajaxStatus;
    }

    public JwtAuthorizationException(AjaxStatus status) {
        this.ajaxStatus=status;
    }





}