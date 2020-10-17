package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sm.cn.entity.BaseGood;

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
public interface IBaseGoodService  {
    /**查询所有
     * @return
     * */

    List<BaseGood> findAll();
    /**
     * 分页查询
     *
     * */
    IPage<BaseGood> pageList(IPage<BaseGood> iPage);

    /**
     * 通过id查询
     * */
BaseGood findById(Serializable id);

void add(BaseGood baseGood);
void update(BaseGood baseGood);
void delete(Serializable id);
}
