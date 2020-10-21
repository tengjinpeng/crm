package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.BaseCustomer;
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
public interface IBaseCategoryService {
    /**查询所有
     * @return
     * */

    List<BaseCategory> findAll();


    PageResult pageList(IPage<BaseCategory> iPage);

    /**
     * 通过id查询
     * */
    BaseCategory findById(Serializable id);

    /**
     *
     * @param baseCategory
     */
    void add(BaseCategory baseCategory);


    void update(BaseCategory baseCategory);
    void delete(Serializable id);

    List<BaseCategory> getCategoryTree();
}
