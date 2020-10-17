package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseUnit;
import com.sm.cn.mapper.BaseUnitMapper;
import com.sm.cn.service.IBaseUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.sql.Wrapper;
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
public class BaseUnitServiceImpl  implements  IBaseUnitService{
@Autowired
BaseUnitMapper baseUnitMapper;
    @Override
    public List<BaseUnit> findAll() {
        return baseUnitMapper.selectList(null);
    }

    @Override
    public IPage<BaseUnit> pageList(IPage<BaseUnit> iPage) {

        return baseUnitMapper.selectPage(iPage,null);
    }



    @Override
    public BaseUnit findById(Serializable id) {
        return baseUnitMapper.selectById(id);
    }

    @Override
    public void add(BaseUnit baseUnit) {
        baseUnitMapper.insert(baseUnit);
    }

    @Override
    public void update(BaseUnit baseUnit) {
            baseUnitMapper.updateById(baseUnit);
    }

    @Override
    public void delete(Serializable id) {

    }
}
