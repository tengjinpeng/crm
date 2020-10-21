package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.BaseCategoryMapper;
import com.sm.cn.mapper.BaseCustomerMapper;
import com.sm.cn.service.IBaseCustomerService;
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
public class BaseCustomerServiceImpl implements IBaseCustomerService {
@Autowired
   BaseCustomerMapper baseCustomerMapper;
    @Override
    public List<BaseCustomer> findAll() {
        return baseCustomerMapper.selectList(null);
    }

    @Override
    public PageResult pageList(IPage<BaseCustomer> iPage) {
        IPage<BaseCustomer> baseCustomerIPage = baseCustomerMapper.selectPage(iPage, null);
        List<BaseCustomer> records = baseCustomerIPage.getRecords();
        long total = baseCustomerIPage.getTotal();
        return PageResult.instance(records,total);
    }

    @Override
    public BaseCustomer findById(Serializable id) {
        return baseCustomerMapper.selectById(id);
    }

    @Override
    public void add(BaseCustomer baseCustomer) {
        baseCustomerMapper.insert(baseCustomer);
    }

    @Override
    public void update(BaseCustomer baseCustomer) {
          baseCustomerMapper.updateById(baseCustomer);
    }

    @Override
    public void delete(Serializable id) {
          baseCustomerMapper.deleteById(id);
    }
}
