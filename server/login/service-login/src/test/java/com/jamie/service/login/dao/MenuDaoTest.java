package com.jamie.service.login.dao;

import com.alibaba.fastjson.JSON;
import com.jamie.service.login.entity.MenuEntity;
import com.jamie.service.login.vo.RoleMenuVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MenuDaoTest {
    private static final Logger logger = LoggerFactory.getLogger(MenuDaoTest.class);

    @Autowired
    private MenuDao menuDao;

    @Test
    public void getRoleMenus() {
        List<RoleMenuVo> vos = menuDao.getRoleMenus();
        logger.info("查询结果：{}", JSON.toJSONString(vos));
    }

    @Test
    public void insertMenu(){
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setPath("/b/**");
        menuDao.insertMenu(menuEntity);
    }
}