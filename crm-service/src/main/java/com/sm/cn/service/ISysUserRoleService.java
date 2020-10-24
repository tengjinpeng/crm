package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysUser;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

public interface ISysUserRoleService {
    /**查询所有
     * @return
     * */

    List<SysUserRole> findAll();

    /***
     * 分页查询
     * @param iPage
     * @return 返回 total data
     */
    PageResult pageList(IPage<SysUserRole> iPage);

    /**
     * 通过id查询
     * */
    SysUserRole findById(Serializable id);


    void add(SysUserRole sysUserRole);
    void update(SysUserRole sysUserRole);
    void delete(Serializable id);


    List<SysRole> findRoleBuyUserId(Serializable userId);

    void deleteByUserIdAndRoleId(Serializable userId,Serializable roleId);

    void deleteAllRoleByUserId(Serializable userId);

}
