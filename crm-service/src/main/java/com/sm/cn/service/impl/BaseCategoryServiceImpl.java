package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.mapper.BaseCategoryMapper;
import com.sm.cn.service.IBaseCategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;

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
@Log4j
public class BaseCategoryServiceImpl implements IBaseCategoryService {
@Autowired
BaseCategoryMapper baseCategoryMapper;

//org.apache.log4j.Logger logger= org.apache.log4j.Logger.getLogger(BaseCategoryServiceImpl.class);

    @Override
    public List<BaseCategory> findAll() {
        return baseCategoryMapper.selectList(null);
    }

    @Override
    public IPage<BaseCategory> pageList(IPage<BaseCategory> iPage) {
        IPage<BaseCategory> page = baseCategoryMapper.selectPage(iPage, null);
//        查看是否有父类，如果有则查询父类名
        page.getRecords().forEach(item->{
            Integer pId = item.getPId();
            if(pId.equals(0)){
                item.setPName("一级分类");
            }else {
                BaseCategory byId = this.findById(pId);
                item.setPName(byId.getName());
            }
        });

        return page;
    }

    @Override
    public BaseCategory findById(Serializable id) {
        return baseCategoryMapper.selectById(id);
    }

    @Override
    public void add(BaseCategory baseCategory) {
        baseCategoryMapper.insert(baseCategory);
    }

    @Override
    public void update(BaseCategory baseCategory) {
         baseCategoryMapper.updateById(baseCategory);
    }

    @Override
    public void delete(Serializable id) {
          baseCategoryMapper.deleteById(id);
    }
}
