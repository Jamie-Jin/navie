package com.jamie.service.login.biz;

import com.jamie.service.login.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizTest {

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
}