package com.sm.cn.http;

public enum AjaxStatus {
    SUCCESS(20000,"操作成功"),
    ERROR(50000,"操作失败"),
    EXT_ERROE(30000,"上传格式不支持"),
    FILE_TOLONG(30001,"文件太大"),
    NOT_IMAGE(30002,"上传的不是图片"),
    VALID_FAILURE(3004,"表单验证失败");
    private int status;
    private String message;

    AjaxStatus(int status,String message) {
        this.status = status;
        this.message=message;
    }

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
