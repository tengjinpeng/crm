package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
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
public interface IBaseUnitService  {
    /**查询所有
     * @return
     * */

    List<BaseUnit> findAll();
    /**
     * 分页查询
     *
     * */
    PageResult pageList(IPage<BaseUnit> iPage);

    /**
     * 通过id查询
     * */
    BaseUnit findById(Serializable id);

    void add(BaseUnit baseUnit);
    void update(BaseUnit baseUnit);
    void delete(Serializable id);
}
