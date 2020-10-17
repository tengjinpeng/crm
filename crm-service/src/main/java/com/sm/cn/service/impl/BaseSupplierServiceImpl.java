package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseSupplier;
import com.sm.cn.mapper.BaseSupplierMapper;
import com.sm.cn.service.IBaseSupplierService;
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
public class BaseSupplierServiceImpl implements  IBaseSupplierService {
@Autowired
BaseSupplierMapper baseSupplierMapper;
    @Override
    public List<BaseSupplier> findAll() {
        return baseSupplierMapper.selectList(null);
    }

    @Override
    public IPage<BaseSupplier> pageList(IPage<BaseSupplier> iPage) {
        return baseSupplierMapper.selectPage(iPage,null);
    }

    @Override
    public BaseSupplier findById(Serializable id) {
        return baseSupplierMapper.selectById(id);
    }

    @Override
    public void add(BaseSupplier baseSupplier) {
        baseSupplierMapper.insert(baseSupplier);
    }

    @Override
    public void update(BaseSupplier baseSupplier) {
        baseSupplierMapper.updateById(baseSupplier);
    }

    @Override
    public void delete(Serializable id) {
       baseSupplierMapper.deleteById(id);
    }
}
