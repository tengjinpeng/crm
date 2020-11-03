package com.sm.cn.controller;

import com.sm.cn.entity.LoginUser;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.entity.SysUser;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import com.sm.cn.mytoken.TokenService;
import com.sm.cn.service.ISysUserService;

import com.sm.cn.useragent.ServletUtils;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */

/**
 * @Auther: tjp
 * @Date: 2020/10/25 14:21
 * @Description:   登录控制器
 * POST请求接收参数 可用对象或Map，如果是Map集合 则通过key获取value
 * 把userId 放到 token  常见做法； 不安全,应该在token中放UUID   把UUID当key  用户信息 当 value 放到redis中*/

@RestController
public class LoginController {
@Autowired
    ISysUserService iSysUserService;
@Autowired
BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
TokenService tokenService;
@PostMapping("login")
    public AjaxResult login(@RequestBody SysUser sysUser){
    String userName=sysUser.getUserName();
    String password=sysUser.getPassword();
     if(StringUtils.isEmpty(userName)){
          return AjaxResult.error(AjaxStatus.USERNAME_NOT_EMPTY);
       }
       if(StringUtils.isEmpty(password)){
         return AjaxResult.error(AjaxStatus.PASSWORD_NOT_EMPTY);
       }
       SysUser sysUser1=iSysUserService.findByUserName(sysUser.getUserName());
        if(Objects.isNull(sysUser1)){
          return AjaxResult.error(AjaxStatus.USERNAME_NOT_SURE);
         }
         boolean matches = bCryptPasswordEncoder.matches(password, sysUser1.getPassword());
        if(!matches){
            return  AjaxResult.error(AjaxStatus.PASSWORD_NOT_SURE);
        }

    String token = tokenService.createTokenAndLoginUser(sysUser1);
    return  AjaxResult.success(token);
}


    @GetMapping("getUserInfo")
    public  AjaxResult getAboutUser(){
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());

        return  AjaxResult.success();
    }



    /***
     * 通过用户id 获得用户的所有信息： 用户权限和动态菜单
     * @param userId
     * @return menuName
     */
    @GetMapping("{userId}/router")
public  AjaxResult getUserRouter(@PathVariable long userId){
    List<SysMenu> routerTreeByUserId = iSysUserService.getRouterTreeByUserId(userId);
return  AjaxResult.success(routerTreeByUserId);
}


}