package com.sm.cn.controller;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.SysUser;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;

import com.sm.cn.service.ISysUserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;


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
    public  AjaxResult add(@RequestBody SysUser sysUser){
        iSysUserService.add(sysUser);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody SysUser sysUser){
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