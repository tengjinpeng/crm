package com.sm.cn.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.service.IBaseGoodService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.security.PublicKey;
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
@RequestMapping("good")
public class BaseGoodController {
@Autowired
    private IBaseGoodService iBaseGoodService;
@GetMapping
    public AjaxResult findAll(){
    List<BaseGood> all = iBaseGoodService.findAll();
    return  AjaxResult.success(all);
}

@GetMapping("page")
    public  AjaxResult pageList(@RequestParam(value = "currentPage",defaultValue= "1") int currentPage,@RequestParam(value = "pageSize" ,defaultValue ="2") int pageSize){
    IPage<BaseGood> page=new Page<>(currentPage,pageSize);
    IPage<BaseGood> baseGoodIPage = iBaseGoodService.pageList(page);
    return AjaxResult.success(PageResult.instance(baseGoodIPage.getRecords(),baseGoodIPage.getTotal()));
}

@PostMapping
    public  AjaxResult add(@RequestBody BaseGood baseGood){
    iBaseGoodService.add(baseGood);
    return AjaxResult.success();
}
@PutMapping
    public  AjaxResult update(@RequestBody BaseGood baseGood){
    iBaseGoodService.update(baseGood);
    return AjaxResult.success();
}
@GetMapping("{id}")
public  AjaxResult fingById(@PathVariable("id") Serializable a){
    BaseGood byId = iBaseGoodService.findById(a);
    return  AjaxResult.success(byId);
}

@DeleteMapping("{id}")
    public  AjaxResult del( @PathVariable("id") Serializable id){
    iBaseGoodService.delete(id);
    return AjaxResult.success();
}

}
