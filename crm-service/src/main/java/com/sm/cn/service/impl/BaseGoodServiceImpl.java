package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.BaseGoodMapper;
import com.sm.cn.service.IBaseGoodService;
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
public class BaseGoodServiceImpl  implements IBaseGoodService {
    @Autowired
    private BaseGoodMapper baseGoodMapper;


    @Override
    public List<BaseGood> findAll() {
        return baseGoodMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<BaseGood> iPage) {
        IPage<BaseGood> baseGoodIPage1 = baseGoodMapper.selectPage(iPage, null);
        List<BaseGood> records = baseGoodIPage1.getRecords();
        long total = baseGoodIPage1.getTotal();

        return PageResult.instance(records,total);
    }

    @Override
    public BaseGood findById(Serializable id) {
        return baseGoodMapper.selectById(id);
    }

    @Override
    public void add(BaseGood baseGood) {
    baseGoodMapper.insert(baseGood);
    }

    @Override
    public void update(BaseGood baseGood) {
     baseGoodMapper.updateById(baseGood);
    }

    @Override
    public void delete(Serializable id) {
    baseGoodMapper.deleteById(id);
    }
}
