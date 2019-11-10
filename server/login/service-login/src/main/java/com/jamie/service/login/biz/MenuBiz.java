package com.jamie.service.login.biz;

import com.jamie.common_util.redis.RedisConstant;
import com.jamie.redis.api.RedisApi;
import com.jamie.service.login.dao.MenuDao;
import com.jamie.service.login.entity.MenuEntity;
import com.jamie.service.login.vo.RoleMenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 访问路径（菜单）服务类
 */
@Service
public class MenuBiz {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private RedisApi redisApi;

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
//        // Redis键
//        String key = RedisConstant.allMenusRoles;
//        List<RoleMenuVo> roleMenuVos = new ArrayList<>();
//
//        // 先从Redis中获取，有则直接返回
//        Object val = redisApi.get(key);
//
//        // Redis中有相关数据
//        if (val != null){
//            roleMenuVos = (List<RoleMenuVo>) val;
//
//        }
//        // Redis中无相关数据
//        else {
//            // Redis中无相关数据，从数据查
//            roleMenuVos = menuDao.getRoleMenus();
//
//            // 从数据库查到数据后插入Redis，使用默认超时时间
//            redisApi.set(key, roleMenuVos);
//        }

        return menuDao.getRoleMenus();
    }
}
