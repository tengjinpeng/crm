package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.BaseUnit;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.BaseUnitMapper;
import com.sm.cn.mapper.SysMenuMapper;
import com.sm.cn.service.IBaseUnitService;
import com.sm.cn.service.ISysMenuService;
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
public class SysMenuServiceImpl implements ISysMenuService {
    @Autowired
    SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> findAll() {
        return sysMenuMapper.selectList(null);
    }



    @Override
    public SysMenu findById(Serializable id) {
        return  sysMenuMapper.selectById(id);
    }

    @Override
    public void add(SysMenu sysMenu) {
            sysMenuMapper.insert(sysMenu);
    }

    @Override
    public void update(SysMenu sysMenu) {
       sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public void delete(Serializable id) {
      sysMenuMapper.deleteById(id);
    }

    @Override
    public List<SysMenu> getMenuTree() {
        List<SysMenu> all = this.findAll();
//        获取一级目录
        List<SysMenu> collect = all.stream().filter(item -> item.getParentId().longValue()==0).collect(Collectors.toList());
//        遍历一级目录
          collect.forEach(e->{
              getMenuChild(e,all);
          });

        return collect;
    }
//递归调用， 获取子菜单集合
    public  void getMenuChild(SysMenu sysMenu,List<SysMenu> all){

        List<SysMenu> collect = all.stream().filter(item -> item.getParentId().equals(sysMenu.getMenuId())).collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(collect)){
            sysMenu.setChildren(collect);
        }

        collect.forEach(e->{
            getMenuChild(e,all);
        });

    }


}
