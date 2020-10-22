package com.sm.cn.controller;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.email.EmailService;
import com.sm.cn.entity.SysUser;
import com.sm.cn.groupvalidator.AddGroup;
import com.sm.cn.groupvalidator.UpdateGroup;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;

import com.sm.cn.service.ISysUserService;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import java.io.IOException;
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
    BCryptPasswordEncoder bCryptPasswordEncoder;
@Autowired
private EmailService emailTest;
@Autowired
private FreeMarkerConfigurer freeMarkerConfigurer;
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
    public  AjaxResult add(@Validated(AddGroup.class) @RequestBody SysUser sysUser ) throws IOException, TemplateException, MessagingException {
//        设置密码并动态加密到数据库，发邮箱验证
         sysUser.setPassword(bCryptPasswordEncoder.encode("123456"));
        freemarker.template.Configuration configuration=freeMarkerConfigurer.getConfiguration();
        Template template=configuration.getTemplate("active.ftl","utf-8");
        Map<String,String> map=new HashMap<>();
        map.put("userName",sysUser.getUserName());
        map.put("phone",sysUser.getPhone());
        map.put("email",sysUser.getEmail());
        map.put("password","123456");
        StringWriter stringWriter=new StringWriter();
        template.process(map,stringWriter);
        emailTest.sendMail(sysUser.getEmail(),stringWriter.toString());
         iSysUserService.add(sysUser);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@Validated(UpdateGroup.class)@RequestBody SysUser sysUser){
        System.out.println(sysUser.toString());
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
        iSysUserService.delete(id);
        return AjaxResult.success();
    }


}
