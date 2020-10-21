package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sm.cn.entity.BaseGood;
import com.sm.cn.entity.BaseSupplier;
import com.sm.cn.entity.BaseUnit;
import com.sm.cn.http.PageResult;

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
public interface IBaseSupplierService  {
    /**查询所有
     * @return
     * */

    List<BaseSupplier> findAll();
    /**
     * 分页查询
     *
     * */
    PageResult pageList(IPage<BaseSupplier> iPage);

    /**
     * 通过id查询
     * */
    BaseSupplier findById(Serializable id);

    void add(BaseSupplier baseSupplier);
    void update(BaseSupplier baseSupplier);
    void delete(Serializable id);
}
