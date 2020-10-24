package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysUser;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.SysUserMapper;
import com.sm.cn.mapper.SysUserRoleMapper;
import com.sm.cn.service.ISysRoleService;
import com.sm.cn.service.ISysUserRoleService;
import com.sm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 用户权限service层实现类
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
@Autowired
    ISysRoleService iSysRoleService;

    @Override
    public List<SysUserRole> findAll() {
        return  sysUserRoleMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<SysUserRole> iPage) {
        IPage<SysUserRole> sysUserRoleIPage = sysUserRoleMapper.selectPage(iPage, null);
        long total = sysUserRoleIPage.getTotal();
        List<SysUserRole> records = sysUserRoleIPage.getRecords();


        return PageResult.instance(records,total);
    }

    @Override
    public SysUserRole findById(Serializable id) {
        return sysUserRoleMapper.selectById(id);
    }

    @Override
    public void add(SysUserRole sysUserRole) {
           sysUserRoleMapper.insert(sysUserRole);
    }

    @Override
    public void update(SysUserRole sysUserRole) {
           sysUserRoleMapper.updateById(sysUserRole);
    }

    @Override
    public void delete(Serializable id) {
 sysUserRoleMapper.deleteById(id);
    }

    @Override
    public List<SysRole> findRoleBuyUserId(Serializable userId) {
        QueryWrapper<SysUserRole> queryWrapper=new QueryWrapper<>();
//       根据用户id 查用户所有的角色
        queryWrapper.lambda().eq(SysUserRole::getUserId,userId);
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWrapper);
        List<SysRole> roles=new ArrayList<>();

       sysUserRoles.forEach(sysUserRole -> {
           SysRole byId = iSysRoleService.findById(sysUserRole.getRoleId());
          roles.add(byId);

       });
        return roles;
    }

    @Override
    public void deleteByUserIdAndRoleId(Serializable userId, Serializable roleId) {
        QueryWrapper<SysUserRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUserRole::getUserId,userId).eq(SysUserRole::getRoleId,roleId);
        sysUserRoleMapper.delete(queryWrapper);
    }

    /***
     * 删除用户的所有角色
     * @param userId
     */
   @Override
    public void deleteAllRoleByUserId(Serializable userId) {
          QueryWrapper<SysUserRole> queryWrapper=new QueryWrapper<>();
          queryWrapper.lambda().eq(SysUserRole::getUserId,userId);
          sysUserRoleMapper.delete(queryWrapper);
    }
}
