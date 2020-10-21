package com.sm.cn.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.BaseCustomer;
import com.sm.cn.http.PageResult;
import com.sm.cn.mapper.BaseCategoryMapper;
import com.sm.cn.service.IBaseCategoryService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

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
    public PageResult pageList(IPage<BaseCategory> iPage) {
        IPage<BaseCategory> page = baseCategoryMapper.selectPage(iPage, null);
        List<BaseCategory> records = page.getRecords();
        long total = page.getTotal();

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

        return PageResult.instance(records,total);
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
/**
 * 查询所有分类
 * */
    @Override
    public List<BaseCategory> getCategoryTree() {
//        所有分类
        List<BaseCategory> all = this.findAll();
//        一级分类集合
        List<BaseCategory> collect = all.stream().filter(baseCategory -> baseCategory.getPId() == 0).collect(Collectors.toList());
            collect.forEach(item->{
              getCategoryChild(item,all);
            });
           return  collect;
    }
    /**
     * 找分类的孩子
     * 传参：父亲list集合和父亲实体
     * */
    public void  getCategoryChild(BaseCategory baseCategory,List<BaseCategory> all){
//找孩子
         List<BaseCategory> collect = all.stream().filter(baseCategory1 -> baseCategory1.getPId().equals(baseCategory.getId())).collect(Collectors.toList());

         if(!CollectionUtils.isEmpty(collect)) {
//         将孩子加入list集合中
             baseCategory.setChildren(collect);
         }
         //         递归继续找
           collect.forEach(baseCategory2 ->{
               getCategoryChild(baseCategory2,all);
           } );


    }
}
