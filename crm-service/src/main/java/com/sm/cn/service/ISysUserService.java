package com.sm.cn.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.sm.cn.entity.SysMenu;
import com.sm.cn.entity.SysUser;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.http.AjaxResult;
import com.sm.cn.http.PageResult;

import java.io.Serializable;
import java.util.List;

/**
 *                    _ooOoo_
 *                   o8888888o
 *                   88" . "88
 *                   (| -_- |)
 *                    O\ = /O
 *                ____/`---'\____
 *              .   ' \\| |// `.
 *               / \\||| : |||// \
 *             / _||||| -:- |||||- \
 *               | | \\\ - /// | |
 *             | \_| ''\---/'' | |
 *              \ .-\__ `-` ___/-. /
 *           ___`. .' /--.--\ `. . __
 *        ."" '< `.___\_<|>_/___.' >'"".
 *       | | : `- \`.;`\ _ /`;.`/ - ` : | |
 *         \ \ `-. \_ __\ /__ _/ .-` / /
 * ======`-.____`-.___\_____/___.-`____.-'======
 *                    `=---='
 *
 * .............................................
 *          佛祖保佑             永无BUG
 */

public interface ISysUserService {
    /**查询所有
     * @return
     * */

    List<SysUser> findAll();

    /***
     * 分页查询
     * @param iPage
     * @return 返回 total data
     */
    PageResult pageList(IPage<SysUser> iPage);

    /**
     * 通过id查询
     * */
    SysUser findById(Serializable id);

    /**
     *
     * @param sysUser
     */
    void add(SysUser sysUser);
    void update(SysUser sysUser);
    void delete(Serializable id);


   SysUser findByUserName(String userName);

 List<SysMenu>   getRouterTreeByUserId(Long userId);
}
