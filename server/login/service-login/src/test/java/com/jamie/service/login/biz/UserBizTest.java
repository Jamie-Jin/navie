package com.jamie.service.login.biz;

import com.jamie.service.login.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizTest {
    private static final Logger logger = LoggerFactory.getLogger(UserBizTest.class);

    @Autowired
    private UserBiz userBiz;

    @Test
    public void createUser() {
        UserVo userVo = new UserVo();
        userVo.setName("Jin");
        userVo.setAccount("jin");
        userVo.setPassword("123456");
        userVo.setRole("role_visitor");
        userBiz.createUser(userVo);
    }

    @Test
    public void createRoleMenu(){
        int result = userBiz.createRoleMenu("ROLE_VISITOR", "/b/**");
        logger.info("插入结果：" + result);
    }

    @Test
    public void createRole(){
        UserVo userVo = new UserVo();
        userVo.setRole("ROLE_VISITOR");
        userVo.setRoleCn("访客");
        logger.info("插入结果：" + userBiz.createRole(userVo));
    }
}