package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.entity.BaseSupplier;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseCustomerService;
import com.sm.cn.service.IBaseSupplierService;
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
@RequestMapping("supplier")
public class BaseSupplierController {
    @Resource
   IBaseSupplierService iBaseSupplierService;
    @GetMapping
    public AjaxResult findAll(){

        return  AjaxResult.success( iBaseSupplierService.findAll());
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1") int currentPage,@RequestParam(defaultValue = "2") int pageSize){
        IPage<BaseSupplier> page=new Page<>(currentPage,pageSize);
        PageResult pageResult = iBaseSupplierService.pageList(page);

        return AjaxResult.success(pageResult);
    }

    @PostMapping
    public  AjaxResult add(@RequestBody BaseSupplier baseCustomer){
        iBaseSupplierService.add(baseCustomer);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody BaseSupplier  baseCustomer){
        iBaseSupplierService.update(baseCustomer);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){

        return  AjaxResult.success(iBaseSupplierService.findById(a));
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
        iBaseSupplierService.delete(id);
        return AjaxResult.success();
    }

}
