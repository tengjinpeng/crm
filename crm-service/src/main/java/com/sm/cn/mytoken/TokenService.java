package com.sm.cn.mytoken;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sm.cn.entity.LoginUser;
import com.sm.cn.entity.SysUser;
import com.sm.cn.redis.RedisCacheService;
import com.sm.cn.useragent.ServletUtils;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.apachecommons.CommonsLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @Auther: tjp
 * @Date: 2020/10/25 19:32
 * @Description: Token 相关方法
 */
@Component

public class TokenService {

    private   String secret = "B5C7FA9A400D9F62";
    private long expireTime=1000 * 60 * 60 * 24 * 5;


@Autowired
    private RedisCacheService redisCacheService;

    /**
     * 封装LoginUser信息
     * @param sysUser
     * @return
     */
    public  String createTokenAndLoginUser(SysUser sysUser) {
        LoginUser loginUser=new LoginUser();
        loginUser.setSysUser(sysUser);
        loginUser.setUuid(UUID.randomUUID().toString());
       this.setLoginUserAgent(loginUser);
        this.setExpireTime(loginUser);
        this.cacheLoginUser(loginUser);

        return  createToken(loginUser.getUuid());
    }


    /**
     * 设置 User-Agent 相关信息
     * @param loginUser
     * ServletUtils获取IP地址以及请求 工具类
     */
    public void setLoginUserAgent(LoginUser loginUser){
        loginUser.setIpAddr(ServletUtils.getIpAddr(ServletUtils.getRequest()));
        loginUser.setLoginLocation(ServletUtils.getLoginLocation(ServletUtils.getRequest()));
        UserAgent userAgent = UserAgent.parseUserAgentString(ServletUtils.getUserAgentStrig(ServletUtils.getRequest()));
        loginUser.setOs(userAgent.getOperatingSystem().getName());
        loginUser.setBroswerName(userAgent.getBrowser().getName());

    }


    public  void setExpireTime(LoginUser loginUser){
        loginUser.setLoginTime(System.currentTimeMillis());
        loginUser.setExpireTime(System.currentTimeMillis()+expireTime);

    }

    /**
     * 给登录用户设置登陆时间，到期时间和缓存数据
     * @param loginUser
     */
    public  void cacheLoginUser(LoginUser loginUser){
        redisCacheService.cacheLoginUser(loginUser.getUuid(),loginUser,expireTime);
    }

    /**
     * 创建token
     * @param uuid
     * @return
     */
    public String createToken(String uuid){

          String  token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim("uuid",uuid)
                    .sign(Algorithm.HMAC256(secret));
        return token;
    }
    public  DecodedJWT  verifierToken (String strToken)  {
         try {
             Algorithm algorithm = Algorithm.HMAC256(secret);
             JWTVerifier verifier = JWT.require(algorithm)
                     .withIssuer("auth0")
                     .build();
             DecodedJWT jwt = verifier.verify(strToken);
             return jwt;

         }catch (JWTVerificationException e){
             e.printStackTrace();
         }
         return null;

    }

    /***
     * 获取token得值
     * @param tokenStr
     * @return jwt.getClaim("uuid").asString()
     */
    public String getTokenByUuid(String tokenStr){
        DecodedJWT jwt = verifierToken(tokenStr);
        return  jwt.getClaim("uuid").asString();

    }

    /**
     * 获得请求头的token
     * @param request
     * @return
     */

    public  String getToken(HttpServletRequest request){
        String authorization = request.getHeader("Authorization");
        return authorization.split(" ")[1];

    }

    /**
     * 获得 登录用户
     * @param httpServletRequest
     * @return
     */
    public  LoginUser getLoginUser(HttpServletRequest httpServletRequest){
        String token = getToken(httpServletRequest);
        String tokenByUuid = getTokenByUuid(token);
        LoginUser loginUser=redisCacheService.getCacheLoginUser(tokenByUuid);
        return  loginUser;
    }

    public  boolean tokenAuthorzation(HttpServletRequest request){
        boolean flag=true;
        String authorization = request.getHeader("Authorization");
        return flag;

    }

}