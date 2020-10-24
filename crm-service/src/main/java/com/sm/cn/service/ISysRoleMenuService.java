package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysRoleMenu;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

public interface ISysRoleMenuService {

    List<SysRoleMenu> findAll();
    PageResult pageList(IPage<SysRoleMenu> iPage);
    SysRoleMenu findById(Serializable id);
    void add(SysRoleMenu SysRoleMenu);
    void update(SysRoleMenu SysRoleMenu);
    void delete(Serializable id);
List<SysRoleMenu> findRoleMenuByRoleId(Serializable roleId);

 void deleteByRoleId(Serializable roleId);

}
