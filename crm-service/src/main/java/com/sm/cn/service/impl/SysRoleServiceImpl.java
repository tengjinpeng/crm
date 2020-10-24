package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.entity.SysRole;
import com.sm.cn.entity.SysRoleMenu;
import com.sm.cn.entity.SysUser;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.SysRoleMapper;
import com.sm.cn.mapper.SysUserMapper;
import com.sm.cn.service.ISysMenuService;
import com.sm.cn.service.ISysRoleMenuService;
import com.sm.cn.service.ISysRoleService;
import com.sm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
public class SysRoleServiceImpl implements ISysRoleService {
@Autowired
SysRoleMapper sysRoleMapper;
@Autowired
    ISysRoleMenuService iSysRoleMenuService;
@Autowired
    ISysMenuService iSysMenuService;
    @Override
    public List<SysRole> findAll() {
        QueryWrapper<SysRole> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().orderByAsc(SysRole::getRoleSort);
        return sysRoleMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<SysRole> iPage) {
        IPage<SysRole> sysRoleIPage = sysRoleMapper.selectPage(iPage, null);
        List<SysRole> records = sysRoleIPage.getRecords();
        long total = sysRoleIPage.getTotal();

        return PageResult.instance(records,total);
    }

    @Override
    public SysRole findById(Serializable id) {
//        获取角色
        SysRole sysRole = sysRoleMapper.selectById(id);
        //查中间表获取所有权限
        List<SysRoleMenu> roleMenuByRoleId = iSysRoleMenuService.findRoleMenuByRoleId(sysRole.getRoleId());
        List<Long> menuIds=new ArrayList<>();
        List<SysMenu> menuList=new ArrayList<>();

        roleMenuByRoleId.forEach(sysRoleMenu -> {
//      找出所有菜单
            SysMenu byId = iSysMenuService.findById(sysRoleMenu.getMenuId());
            menuList.add(byId);
        });
//        过滤目录级别的菜单 拿出菜单和按钮
        List<SysMenu> m = menuList.stream().filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase("M")).collect(Collectors.toList());
//                  判断有没有孩子
             m.forEach(sysMenu -> {
                 if(!hasChildren(sysMenu,m)){
                     menuIds.add(sysMenu.getMenuId());
                 }
             });
           sysRole.setMenuIds(menuIds);
        return sysRole;
    }

//    是否有孩子
    public  boolean hasChildren(SysMenu sysMenu,List<SysMenu> list){

        return  list.stream().anyMatch(sysMenu1 -> sysMenu1.getParentId().longValue() == sysMenu.getMenuId().longValue());
    }

    @Override
    public void add(SysRole sysRole) {
       sysRoleMapper.insert(sysRole);
//       添加完后 向 Rolemenu中间表中设置 roleid 和menuIds
        List<Long> menuIds = sysRole.getMenuIds();
        if(!CollectionUtils.isEmpty(menuIds)){
            menuIds.forEach(item->{
                SysRoleMenu sysRoleMenu=new SysRoleMenu();
                sysRoleMenu.setMenuId(item);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                iSysRoleMenuService.add(sysRoleMenu);
            });

        }
    }

    @Override
    public void update(SysRole sysRole) {
      sysRoleMapper.updateById(sysRole);
//      先删除后 在向中间表添加新数据
        iSysRoleMenuService.deleteByRoleId(sysRole.getRoleId());
        List<Long> menuIds = sysRole.getMenuIds();
        if(!CollectionUtils.isEmpty(menuIds)){
            menuIds.forEach(item->{
                SysRoleMenu sysRoleMenu=new SysRoleMenu();
                sysRoleMenu.setMenuId(item);
                sysRoleMenu.setRoleId(sysRole.getRoleId());
                iSysRoleMenuService.add(sysRoleMenu);

            });
        }


    }

    @Override
    public void delete(Serializable id) {
         sysRoleMapper.deleteById(id);
    }
}
