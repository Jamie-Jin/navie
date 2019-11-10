package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.common_util.redis.RedisConstant;
import com.jamie.service.login.entity.MenuEntity;
import com.jamie.service.login.vo.RoleMenuVo;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MenuDao extends BaseDao<MenuEntity> {

    public int insertMenu(MenuEntity entity){
        return singleInsert(entity);
    }

    public MenuEntity getMenu(MenuEntity menuEntity){
        HashMap<String, Object> param = new HashMap<>();
        param.put("path", menuEntity.getPath());
        return getSqlSessionTemplate().selectOne(getStatement("getMenu"), param);
    }

    // 获取所有的路径，以及路径对应的角色信息
    // 使用@Cacheable将结果缓存到Redis中
    @Cacheable(value = RedisConstant.allMenusRoles, key = RedisConstant.allMenusRoles)
    public List<RoleMenuVo> getRoleMenus(){
        return getSqlSessionTemplate().selectList(getStatement("getRoleMenus"));
    }

}
