package com.sm.cn.Interceptor;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.sm.cn.exception.JwtAuthorizationException;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import com.sm.cn.mytoken.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auther: tjp
 * @Date: 2020/10/25 22:46
 * @Description:
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
   private TokenService tokenService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        /**session 方式登录拦截 ： 它是有状态的 多个服务器 需共享session 可以攻击网站，数据不安全等
//         * 前后端分离时，需要同时允许cookie
//         * 解决思想：每一次请求的时候 前端在请求头当中，携带后台能识别的内容,这种方式无状态，如果不加密的话，不安全
//         * 所携带的内容常用的 JWT（java web Token）
//         * */
//
        String authorization = request.getHeader("Authorization");
        System.out.println("请求头 : "+authorization);
        if(!StringUtils.isEmpty(authorization)&&authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            DecodedJWT jwt = tokenService.verifierToken(token);
            String tokenByUuid = tokenService.getTokenByUuid(token);
            System.out.println("tokenId  : "+tokenByUuid);
            if(jwt==null){

                throw new JwtAuthorizationException(AjaxStatus.TOKEN_VALID_FAILURE);
            }else {
                //解析成功
//                Long userId = jwt.getClaim("userId").asLong();
//                request.setAttribute("userId",userId);

            }
        }else {
            throw new JwtAuthorizationException(AjaxStatus.TOKEN_ERROR);
        }

        return true;
    }

}