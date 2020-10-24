package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.entity.SysRole;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseCustomerService;
import com.sm.cn.service.ISysRoleService;
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
@RequestMapping("role")
public class SysRoleController {
    @Resource
    ISysRoleService iSysRoleService;
    @GetMapping
    public AjaxResult findAll(){
        List<SysRole> all = iSysRoleService.findAll();
        return  AjaxResult.success(all);
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1") int currentPage,@RequestParam(defaultValue = "2") int pageSize){
        IPage<SysRole> iPage=new Page<>(currentPage,pageSize);
        PageResult pageResult = iSysRoleService.pageList(iPage);
        return AjaxResult.success(pageResult);
    }

    @PostMapping
    public  AjaxResult add(@RequestBody SysRole sysRole){
        System.out.println("添加的权限"+sysRole.toString());
        iSysRoleService.add(sysRole);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody SysRole sysRole){
       iSysRoleService.update(sysRole);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){

        return  AjaxResult.success(iSysRoleService.findById(a));
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
       iSysRoleService.delete(id);
        return AjaxResult.success();
    }

}
