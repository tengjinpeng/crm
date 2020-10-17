package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseCategoryService;
import com.sm.cn.service.IBaseCustomerService;
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
@RequestMapping("customer")
public class BaseCustomerController {
    @Resource
   IBaseCustomerService iBaseCustomerService;
    @GetMapping
    public AjaxResult findAll(){
        List<BaseCustomer> all = iBaseCustomerService.findAll();
        return  AjaxResult.success(all);
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1") int currentPage,@RequestParam(defaultValue = "2") int pageSize){
        IPage<BaseCustomer> page=new Page<>(currentPage,pageSize);
        IPage<BaseCustomer> baseGoodIPage = iBaseCustomerService.pageList(page);
        return AjaxResult.success(PageResult.instance(baseGoodIPage.getRecords(),baseGoodIPage.getTotal()));
    }

    @PostMapping
    public  AjaxResult add(@RequestBody BaseCustomer baseCustomer){
        iBaseCustomerService.add(baseCustomer);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody BaseCustomer baseCustomer){
        iBaseCustomerService.update(baseCustomer);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){

        return  AjaxResult.success(iBaseCustomerService.findById(a));
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
       iBaseCustomerService.delete(id);
        return AjaxResult.success();
    }

}
