package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.*;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.AjaxStatus;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.*;
import com.sm.cn.service.IBaseSupplierService;
import com.sm.cn.service.ISysMenuService;
import com.sm.cn.service.ISysUserRoleService;
import com.sm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
public class SysUserServiceImpl implements ISysUserService {
@Autowired
    SysUserMapper sysUserMapper;
@Autowired
    ISysUserRoleService iSysUserRoleService;
@Autowired
    SysUserRoleMapper sysUserRoleMapper;
@Autowired
    SysRoleMenuMapper sysRoleMenuMapper;
@Autowired
    ISysMenuService iSysMenuService;

    @Override
    public List<SysUser> findAll() {
        return sysUserMapper.selectList(null);
    }

    /***
     * 分页查询
     * @param iPage
     * @return
     */
    @Override
    public PageResult pageList(IPage<SysUser> iPage) {
       IPage<SysUser> sysUserIPage=sysUserMapper.selectPage(iPage,null);
            List<SysUser> records = sysUserIPage.getRecords();
            long total = sysUserIPage.getTotal();
        return PageResult.instance(records,total);
    }

    @Override
    public SysUser findById(Serializable id) {

        return sysUserMapper.selectById(id);
    }

    @Override
    public void add(SysUser sysUser) {
           sysUserMapper.insert(sysUser);
           //添加用户角色     调用service层
        String roleIds = sysUser.getRoleIds();
        String[] as = roleIds.split("A");
        Arrays.asList(as).forEach(e->{
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(Long.parseLong(e));
          iSysUserRoleService.add(sysUserRole);
        });
    }

    @Override
    public void update(SysUser sysUser) {
      sysUserMapper.updateById(sysUser);
      //先删除全部用户角色  后 添加
      iSysUserRoleService.deleteAllRoleByUserId(sysUser.getUserId());

        String roleIds = sysUser.getRoleIds();
        String[] as = roleIds.split("A");
        Arrays.asList(as).forEach(e->{
            SysUserRole sysUserRole=new SysUserRole();
            sysUserRole.setUserId(sysUser.getUserId());
            sysUserRole.setRoleId(Long.parseLong(e));
            iSysUserRoleService.add(sysUserRole);
        });
    }

    @Override
    public void delete(Serializable id) {
           sysUserMapper.deleteById(id);
    }

    /***
     *  根据登录名查询用户
     * @param userName
     * @return
     */
    @Override
    public SysUser findByUserName(String userName) {

        QueryWrapper<SysUser> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysUser::getUserName,userName);
        List<SysUser> sysUsers = sysUserMapper.selectList(queryWrapper);
        if(CollectionUtils.isEmpty(sysUsers)){
            return  null;
        }
        return sysUsers.get(0);
    }

    /***
     * 根据userId查出所有用户的权限（权限树）
     * @param userId
     * @return
     */
    @Override
    public List<SysMenu> getRouterTreeByUserId(Long userId) {
       QueryWrapper<SysUserRole> queryWrapper=new QueryWrapper<>();
       queryWrapper.lambda().eq(SysUserRole::getUserId,userId);
//    UserRole中间表  通过userId 查找该用户的所有角色
        List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectList(queryWrapper);
//    RoleMenu中间表     通过roleId  查找 该角色下所有权限id
     QueryWrapper<SysRoleMenu> queryWrapper1=new QueryWrapper<>();
     List<SysMenu> list=new ArrayList<>();

      sysUserRoles.forEach(sysUserRole -> {
          queryWrapper1.lambda().eq(SysRoleMenu::getRoleId,sysUserRole.getRoleId());
//          遍历权限id,找出所有权限 添加到list集合中
          List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(queryWrapper1);
          sysRoleMenus.forEach(sysRoleMenu -> {
              SysMenu menu = iSysMenuService.findById(sysRoleMenu.getMenuId());
           list.add(menu);
          });

      });
//       只留目录和菜单  过滤F级别的菜单
        Stream<SysMenu> f = list.stream().filter(sysMenu -> !sysMenu.getMenuType().equalsIgnoreCase("F"));
        List<SysMenu> collect = f.collect(Collectors.toList());
//        形成 menutree   找出一级菜单  并调用找孩子方法递归遍历找出所有子类
        Stream<SysMenu> sysMenuStream = collect.stream().filter(sysMenu -> sysMenu.getParentId() == 0);
        List<SysMenu> root = sysMenuStream.collect(Collectors.toList());
        root.forEach(sysMenu -> {
            getMenuChiledren(sysMenu,collect);
        });


        return root;
    }

    /**
     * 递归寻找孩子
     * @param sysMenu
     * @param all
     */
    public  void  getMenuChiledren(SysMenu sysMenu, List<SysMenu> all){

        Stream<SysMenu> sysMenuStream = all.stream().filter(sysMenu1 -> sysMenu1.getParentId().longValue()== sysMenu.getMenuId());
        List<SysMenu> collect = sysMenuStream.collect(Collectors.toList());

        if(!CollectionUtils.isEmpty(collect)){
             sysMenu.setChildren(collect);
        }
        collect.forEach(e->{
            getMenuChiledren(e,all);
        });

    }

}
