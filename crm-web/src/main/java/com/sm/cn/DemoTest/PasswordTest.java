package com.sm.cn.DemoTest;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Auther: tjp
 * @Date: 2020/10/22 16:06
 * @Description:
 */
public class PasswordTest {
    /***
     *  BCrypt动态加密
     * @param args
     */
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder=new BCryptPasswordEncoder();
        BCryptPasswordEncoder encoder1=new BCryptPasswordEncoder();
        String a="123";
        System.out.println(encoder.encode(a));
        System.out.println(encoder1.encode(a));
        System.out.println(encoder.matches(a, "$2a$10$/PnRSUEIYDQCBMYEsd3aEO5P.50uojkVnX1bnoQ6/wyH4BEDOPW82"));
    }
}