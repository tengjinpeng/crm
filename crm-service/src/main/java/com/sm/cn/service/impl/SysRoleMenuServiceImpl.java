package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.entity.SysRoleMenu;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.SysMenuMapper;
import com.sm.cn.mapper.SysRoleMenuMapper;
import com.sm.cn.service.ISysMenuService;
import com.sm.cn.service.ISysRoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
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
public class SysRoleMenuServiceImpl implements ISysRoleMenuService {
 @Autowired
    SysRoleMenuMapper sysRoleMenuMapper;


    @Override
    public List<SysRoleMenu> findAll() {
        return  sysRoleMenuMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<SysRoleMenu> iPage) {
        IPage<SysRoleMenu> sysRoleMenuIPage = sysRoleMenuMapper.selectPage(iPage, null);
        List<SysRoleMenu> records = sysRoleMenuIPage.getRecords();
        long total = sysRoleMenuIPage.getTotal();
        return PageResult.instance(records,total);
    }


    @Override
    public SysRoleMenu findById(Serializable id) {
        return sysRoleMenuMapper.selectById(id);
    }

    @Override
    public void add(SysRoleMenu sysRoleMenu) {

        sysRoleMenuMapper.insert(sysRoleMenu);

    }

    @Override
    public void update(SysRoleMenu sysRoleMenu) {
            sysRoleMenuMapper.updateById(sysRoleMenu);
    }

    @Override
    public void delete(Serializable id) {
         sysRoleMenuMapper.deleteById(id);
    }
/*通过角色id 查所有菜单*/
    @Override
    public List<SysRoleMenu> findRoleMenuByRoleId(Serializable roleId) {
        QueryWrapper<SysRoleMenu> queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysRoleMenu::getRoleId,roleId);
        List<SysRoleMenu> sysRoleMenus = sysRoleMenuMapper.selectList(queryWrapper);
        return sysRoleMenus;
    }

    @Override
    public void deleteByRoleId(Serializable roleId) {
         QueryWrapper<SysRoleMenu> queryWrapper=new QueryWrapper<>();
         queryWrapper.lambda().eq(SysRoleMenu::getRoleId,roleId);
        sysRoleMenuMapper.delete(queryWrapper);
    }
}
