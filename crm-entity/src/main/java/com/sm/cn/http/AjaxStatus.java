package com.sm.cn.http;

public enum AjaxStatus {
    SUCCESS(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    EXT_ERROE(30000,"上传格式不支持"),
    FILE_TOLONG(30001,"文件太大"),
    NOT_IMAGE(30002,"上传的不是图片"),
    VALID_FAILURE(3004,"表单验证失败"),

    USERNAME_NOT_SURE(40003,"用户名不对"),
    PASSWORD_NOT_SURE(40004,"密码不对"),
    USERNAME_NOT_EMPTY(40005,"用户名不能为空"),
    PASSWORD_NOT_EMPTY(40006,"密码不能为空"),
    LOGIN_SUCCESS(400007,"登录成功"),

    TOKEN_VALID_FAILURE(40004,"token未认证"),
    TOKEN_ERROR(40005,"token不正确");
    private int status;
    private String message;

    AjaxStatus(int status,String message) {
        this.status = status;
        this.message=message;
    }
    AjaxStatus(){}

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
