package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

public interface ISysMenuService {


    List<SysMenu> findAll();


    SysMenu findById(Serializable id);

    void add(SysMenu sysMenu);
    void update(SysMenu sysMenu);
    void delete(Serializable id);

    List<SysMenu> getMenuTree();
}
