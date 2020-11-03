package com.sm.cn.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Auther: tjp
 * @Date: 2020/10/28 19:58
 * @Description:  实体类 记录每次操作详情包括登录日志和用户信息
 */
@Data
@Getter
@Setter

public class LoginUser {
    private String uuid;
    private SysUser sysUser;
    private String os;
    private String ipAddr;
    private String broswerName;
    private String loginLocation;
    private long loginTime;
    private long expireTime;
}