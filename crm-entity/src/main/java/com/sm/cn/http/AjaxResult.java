package com.sm.cn.http;

import com.sm.cn.entity.BaseGood;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

/**
 * @Auther: tjp
 * @Date: 2020/10/17 19:39
 * @Description:
 */
public class AjaxResult extends HashMap<String,Object> {
    private  static final String  STATUS="status";
    private  static final String  MESSAGE="message";
    private  static final String  DATA="data";

    public AjaxResult(AjaxStatus ajaxStatus) {
     put(STATUS,ajaxStatus.getStatus());
     put(MESSAGE,ajaxStatus.getMessage());
    }

    public AjaxResult() {
    }

//  成功
public  static  AjaxResult success(){
        return new AjaxResult(AjaxStatus.SUCCESS);
}
    public  static  AjaxResult success(AjaxStatus ajaxStatus){
        return new AjaxResult(ajaxStatus);
    }

    public  static  AjaxResult success(Object obj){
    AjaxResult ajaxResult=new AjaxResult(AjaxStatus.SUCCESS);
    ajaxResult.put(DATA,obj);
    return ajaxResult;
    }
    //失败
    public  static  AjaxResult  error(){
        return  new  AjaxResult(AjaxStatus.ERROR);
    }
    public  static  AjaxResult error(AjaxStatus ajaxStatus){
        return new AjaxResult(ajaxStatus);
    }
}