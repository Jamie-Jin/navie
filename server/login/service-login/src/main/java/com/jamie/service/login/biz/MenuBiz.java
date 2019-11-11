package com.jamie.service.login.biz;

import com.jamie.api.login.entity.MenuEntity;
import com.jamie.api.login.vo.RoleMenuVo;
import com.jamie.redis.api.RedisApi;
import com.jamie.service.login.dao.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 访问路径（菜单）服务类
 */
@Service
public class MenuBiz {

    @Autowired
    private MenuDao menuDao;

    // 插入菜单
    public int insertMenu(MenuEntity menuEntity){
        return menuDao.insertMenu(menuEntity);
    }

    // 根据条件查询菜单
    public MenuEntity getMenu(MenuEntity menuEntity){
        return menuDao.getMenu(menuEntity);
    }

    // 获取所有的路径，以及路径对应的角色信息
    public List<RoleMenuVo> getRoleMenus(){
        return menuDao.getRoleMenus();
    }
}
