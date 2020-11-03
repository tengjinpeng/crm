package com.sm.cn.service.impl;

import com.sm.cn.entity.SysLoginLog;
import com.sm.cn.mapper.SysLoginLogMapper;
import com.sm.cn.service.ISysLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Auther: tjp
 * @Date: 2020/10/26 20:53
 * @Description:
 */
@Service
@Transactional
public class SysLoginLogServiceImpl implements ISysLoginLogService {
    @Autowired
  private SysLoginLogMapper sysLoginLogMapper;

    @Override
    public void addLog(SysLoginLog sysLoginLog) {
         sysLoginLogMapper.insert(sysLoginLog);
    }
}