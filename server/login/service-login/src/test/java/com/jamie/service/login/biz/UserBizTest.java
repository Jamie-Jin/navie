package com.jamie.service.login.biz;

import com.jamie.service.login.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserBizTest {
    private static final Logger logger = LoggerFactory.getLogger(UserBizTest.class);

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 创建用户与角色的关系
    @Test
    public void createUser() {
        UserVo userVo = new UserVo();
        userVo.setName("Jamie Jin");
        userVo.setAccount("jamie");
        userVo.setPassword(passwordEncoder.encode("123456"));
        userVo.setRole("ROLE_ADMIN");
        userBiz.createUser(userVo);
    }

    // 创建访问路径与角色的关系
    @Test
    public void createRoleMenu(){
        int result = userBiz.createRoleMenu("ROLE_ADMIN", "/a/**");
        logger.info("插入结果：" + result);
    }

    // 创建角色
    @Test
    public void createRole(){
        UserVo userVo = new UserVo();
        userVo.setRole("ROLE_ADMIN");
        userVo.setRoleCn("管理员");
        logger.info("插入结果：" + userBiz.createRole(userVo));
    }
}