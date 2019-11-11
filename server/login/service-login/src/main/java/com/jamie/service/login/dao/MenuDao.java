package com.jamie.service.login.dao;

import com.jamie.api.login.entity.MenuEntity;
import com.jamie.api.login.vo.RoleMenuVo;
import com.jamie.common_dao.BaseDao;
import com.jamie.common_util.redis.RedisConstant;
import org.springframework.cache.annotation.CacheEvict;
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
    // @Cacheable：先读取redis看有没有缓存，没有的话直接读数据库，然后将结果写入redis中
    // value：缓存名称，key：缓存的key（可以不写的），condition：将数据放入缓存的条件
    @Cacheable(value = RedisConstant.allMenusRoles)
    public List<RoleMenuVo> getRoleMenus(){
        return getSqlSessionTemplate().selectList(getStatement("getRoleMenus"));
    }

}
