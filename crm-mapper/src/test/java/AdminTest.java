import com.sm.cn.eneity.Admin;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Auther: tjp
 * @Date: 2020/10/16 22:21
 * @Description:
 */

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {"classpath:application-mapper.xml"})
public class AdminTest {
    @Autowired
    AdminMapper adminMapper;
    @Test
    public void asd(){
        List<Admin> all = adminMapper.findAll();
        System.out.println(all);
    }
}