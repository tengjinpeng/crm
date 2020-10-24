package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.ISysMenuService;

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
@RequestMapping("menu")
public class SysMenuController {
    @Resource
    ISysMenuService iSysMenuService;
    @GetMapping("menuTree")
    public AjaxResult findAll(){
        List<SysMenu> all = iSysMenuService.getMenuTree();
        return  AjaxResult.success(all);
    }



    @PostMapping
    public  AjaxResult add(@RequestBody SysMenu SysMenu){
        iSysMenuService.add(SysMenu);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody SysMenu SysMenu){
       iSysMenuService.update(SysMenu);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){

        return  AjaxResult.success(iSysMenuService.findById(a));
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
       iSysMenuService.delete(id);
        return AjaxResult.success();
    }

}
