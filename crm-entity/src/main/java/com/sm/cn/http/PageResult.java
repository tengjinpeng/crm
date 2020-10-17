package com.sm.cn.http;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Auther: tjp
 * @Date: 2020/10/17 19:52
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
private Object records;
private long total;
public  static  PageResult instance(Object records,long total){
    return new PageResult(records,total);
}
}