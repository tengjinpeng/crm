import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sm.cn.entity.BaseCategory;
import com.sm.cn.entity.SysUserRole;
import com.sm.cn.service.IBaseCategoryService;
import com.sm.cn.service.ISysUserRoleService;
import com.sm.cn.service.ISysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.logging.Logger;

/**
 * @Auther: tjp
 * @Date: 2020/10/17 19:31
 * @Description:
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath*:application-mapper.xml","classpath:application-service.xml"})
public class DEMOTest {
    @Autowired
    IBaseCategoryService iBaseCategoryService;
@Autowired
    ISysUserRoleService iSysUserRoleService;
    @Test
    public  void a(){
//        IPage<BaseCategory> page=new Page<>(1,2);
//        IPage<BaseCategory> as = iBaseCategoryService.pageList(page);
//        System.out.println(page==as);
//        System.out.println(iBaseCategoryService.getCategoryTree());
        List<SysUserRole> all = iSysUserRoleService.findAll();
        System.out.println(all);


    }
}
