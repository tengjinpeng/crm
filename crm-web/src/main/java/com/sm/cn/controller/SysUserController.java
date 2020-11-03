package com.sm.cn.controller;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.sm.cn.async.AsyncManager;
import com.sm.cn.async.AsynvFactory;
import com.sm.cn.email.EmailService;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysUser;
import com.sm.cn.groupvalidator.AddGroup;
import com.sm.cn.groupvalidator.UpdateGroup;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;

import com.sm.cn.service.ISysUserRoleService;
import com.sm.cn.service.ISysUserService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;


import javax.annotation.Resource;


import java.io.Serializable;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author tjp
 * @since 2020-10-17
 */
@RestController
@RequestMapping("user")
public class SysUserController {
@Resource
ISysUserService iSysUserService;
@Autowired
private BCryptPasswordEncoder bCryptPasswordEncoder;
//@Autowired
//private EmailService emailService;
//@Autowired
//private FreeMarkerConfigurer freeMarkerConfigurer;
@Autowired
private ISysUserRoleService iSysUserRoleService;

    @GetMapping
    public AjaxResult findAll(){
        List<SysUser> all = iSysUserService.findAll();
        return  AjaxResult.success(all);
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1")int  currentPage,
                                @RequestParam(defaultValue = "2") int  pageSize){
        IPage<SysUser> page=new Page<>(currentPage,pageSize);
        PageResult pageResult = iSysUserService.pageList(page);
        return AjaxResult.success(pageResult);
    }


    @PostMapping
    public  AjaxResult add(@Validated(AddGroup.class) @RequestBody SysUser sysUser ) throws Exception {
        System.out.println("后台数据 ："+sysUser.toString());

        sysUser.setPassword(bCryptPasswordEncoder.encode("123456"));
         iSysUserService.add(sysUser);
//        Configuration configuration =freeMarkerConfigurer.getConfiguration();
//        Template template = configuration.getTemplate("user.ftl", "utf-8");
//        Map<String,String> map=new HashMap<>();
//        map.put("userName",sysUser.getUserName());
//        map.put("password", "123456");
//        StringWriter stringWriter=new StringWriter();
//        template.process(map,stringWriter);
////        异步任务调度发送邮件
//        AsyncManager.getInstance().executeTask(AsynvFactory.executeEmail(sysUser.getEmail(),stringWriter.toString()));
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@Validated(UpdateGroup.class)@RequestBody SysUser sysUser){
        System.out.println("修改后 ： "+sysUser.toString());
        iSysUserService.update(sysUser);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){
        SysUser byId = iSysUserService.findById(a);
        return  AjaxResult.success(byId);
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
        SysUser byId = iSysUserService.findById(id);
        byId.setDelFlag(2);
        iSysUserService.update(byId);
        return AjaxResult.success();
    }

    /**
     * 根据用户id查用户的所有角色
     * @param userId
     * @return
     */
    @GetMapping({"{userId}/roles"})
    public  AjaxResult getUserRoleByUserId(@PathVariable Serializable userId){
        List<SysRole> roleBuyUserId = iSysUserRoleService.findRoleBuyUserId(userId);

        return  AjaxResult.success(roleBuyUserId);
    }
@DeleteMapping("{userId}/role/{roleId}")
    public  AjaxResult getRoleByUserId(@PathVariable Serializable userId,@PathVariable Serializable roleId){

              iSysUserRoleService.deleteByUserIdAndRoleId(userId,roleId);
    return AjaxResult.success();
    }


}
