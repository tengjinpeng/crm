package com.sm.cn.controller;




import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("category")
public class BaseCategoryController {
@Resource
    IBaseCategoryService iBaseCategoryService;
    @GetMapping
    public AjaxResult findAll(){
        List<BaseCategory> all = iBaseCategoryService.findAll();
        return  AjaxResult.success(all);
    }

    @GetMapping("page")
    public  AjaxResult pageList(@RequestParam(defaultValue = "1") int currentPage,@RequestParam(defaultValue = "2") int pageSize){
        IPage<BaseCategory> page=new Page<>(currentPage,pageSize);
        IPage<BaseCategory> baseGoodIPage = iBaseCategoryService.pageList(page);
        return AjaxResult.success(PageResult.instance(baseGoodIPage.getRecords(),baseGoodIPage.getTotal()));
    }

    @PostMapping
    public  AjaxResult add(@RequestBody BaseCategory baseCategory){
        iBaseCategoryService.add(baseCategory);
        return AjaxResult.success();
    }
    @PutMapping
    public  AjaxResult update(@RequestBody BaseCategory baseCategory){
        iBaseCategoryService.update(baseCategory);
        return AjaxResult.success();
    }
    @GetMapping("{id}")
    public  AjaxResult fingById(@PathVariable("id") Serializable a){
        BaseCategory byId = iBaseCategoryService.findById(a);
        return  AjaxResult.success(byId);
    }

    @DeleteMapping("{id}")
    public  AjaxResult del(@PathVariable("id") Serializable id){
        iBaseCategoryService.delete(id);
        return AjaxResult.success();
    }


}
