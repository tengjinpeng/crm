package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.entity.BaseUnit;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author tjp
 * @since 2020-10-17
 */
public interface IBaseCustomerService  {
    /**查询所有
     * @return
     * */

    List<BaseCustomer> findAll();
    /**
     * 分页查询
     *
     * */
    IPage<BaseCustomer> pageList(IPage<BaseCustomer> iPage);

    /**
     * 通过id查询
     * */
    BaseCustomer findById(Serializable id);

    void add(BaseCustomer baseCustomer);
    void update(BaseCustomer baseCustomer);
    void delete(Serializable id);
}
