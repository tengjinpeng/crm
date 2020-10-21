package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

public interface ISysMenuService {
    /**查询所有
     * @return
     * */

    List<SysMenu> findAll();
    /**
     * 分页查询
     *
     * */
    PageResult pageList(IPage<SysMenu> iPage);

    /**
     * 通过id查询
     * */
    SysMenu findById(Serializable id);

    void add(SysMenu sysMenu);
    void update(SysMenu sysMenu);
    void delete(Serializable id);
}
