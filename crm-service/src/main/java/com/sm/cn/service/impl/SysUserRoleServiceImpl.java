package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysUser;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.SysUserMapper;
import com.sm.cn.mapper.SysUserRoleMapper;
import com.sm.cn.service.ISysUserRoleService;
import com.sm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author tjp
 * @since 2020-10-17
 */
@Service
@Transactional
public class SysUserRoleServiceImpl implements ISysUserRoleService {
@Autowired
    SysUserRoleMapper sysUserRoleMapper;
    @Override
    public List<SysUserRole> findAll() {
        return sysUserRoleMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<SysUserRole> iPage) {
        IPage<SysUserRole> sysUserRoleIPage = sysUserRoleMapper.selectPage(iPage, null);
        List<SysUserRole> records = sysUserRoleIPage.getRecords();
        long total = sysUserRoleIPage.getTotal();

        return PageResult.instance(records,total);
    }

    @Override
    public SysUserRole findById(Serializable id) {
        return null;
    }

    @Override
    public void add(SysUserRole sysUserRole) {

    }

    @Override
    public void update(SysUserRole sysUserRole) {

    }

    @Override
    public void delete(Serializable id) {

    }
}
