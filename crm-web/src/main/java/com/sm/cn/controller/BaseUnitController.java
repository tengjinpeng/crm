package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseSupplier;
import com.sm.cn.entity.BaseUnit;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseSupplierService;
import com.sm.cn.service.IBaseUnitService;
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
@RequestMapping("unit")
public class BaseUnitController {
    @Resource
   IBaseUnitService iBaseUnitService;
    @GetMapping
    public AjaxResult findAll(){

        return  AjaxResult.success( iBaseUnitService.findAll());
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1") int currentPage,@RequestParam(defaultValue = "2") int pageSize){
        IPage<BaseUnit> page=new Page<>(currentPage,pageSize);
        IPage<BaseUnit> baseGoodIPage = iBaseUnitService.pageList(page);
        return AjaxResult.success(PageResult.instance(baseGoodIPage.getRecords(),baseGoodIPage.getTotal()));
    }

    @PostMapping
    public  AjaxResult add(@RequestBody BaseUnit baseCustomer){
        iBaseUnitService.add(baseCustomer);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody BaseUnit  baseCustomer){
        iBaseUnitService.update(baseCustomer);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){

        return  AjaxResult.success(  iBaseUnitService.findById(a));
    }

    @DeleteMapping
    public  AjaxResult del(Serializable id){
        iBaseUnitService.delete(id);
        return AjaxResult.success();
    }
}
