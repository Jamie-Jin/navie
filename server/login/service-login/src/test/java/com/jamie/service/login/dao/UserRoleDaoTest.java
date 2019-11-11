package com.jamie.service.login.dao;

import com.alibaba.fastjson.JSON;
import com.jamie.api.login.vo.UserRoleVo;
import com.jamie.api.login.vo.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(UserRoleDaoTest.class);

    @Autowired
    private UserRoleDao userRoleDao;

    @Test
    public void getUserRole() {
        UserVo userVo = new UserVo();
        userVo.setAccount("jamie");
        UserRoleVo userRoleVo = userRoleDao.getUserRole(userVo);
        logger.info("查询结果：" + JSON.toJSONString(userRoleVo));
    }
}