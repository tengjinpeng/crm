package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysUser;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

public interface ISysRoleService {
    /**查询所有
     * @return
     * */

    List<SysRole> findAll();

    /***
     * 分页查询
     * @param iPage
     * @return 返回 total data
     */
    PageResult pageList(IPage<SysUser> iPage);

    /**
     * 通过id查询
     * */
    SysRole findById(Serializable id);


    void add(SysRole sysRole);
    void update(SysRole sysRole);
    void delete(Serializable id);

}
