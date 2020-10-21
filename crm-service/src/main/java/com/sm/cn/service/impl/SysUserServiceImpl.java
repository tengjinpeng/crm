package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseSupplier;
import com.sm.cn.entity.SysUser;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.BaseSupplierMapper;
import com.sm.cn.mapper.SysUserMapper;
import com.sm.cn.service.IBaseSupplierService;
import com.sm.cn.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
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
public class SysUserServiceImpl implements ISysUserService {
@Autowired
    SysUserMapper sysUserMapper;

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
    }

    @Override
    public void update(SysUser sysUser) {
      sysUserMapper.updateById(sysUser);
    }

    @Override
    public void delete(Serializable id) {
           sysUserMapper.deleteById(id);
    }
}
