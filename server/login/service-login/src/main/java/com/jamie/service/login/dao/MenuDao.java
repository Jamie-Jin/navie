package com.jamie.service.login.dao;

import com.jamie.common_dao.BaseDao;
import com.jamie.service.login.entity.MenuEntity;
import com.jamie.service.login.vo.RoleMenuVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MenuDao extends BaseDao<MenuEntity> {

    public int insertMenu(MenuEntity entity){
        return singleInsert(entity);
    }

    public List<RoleMenuVo> getRoleMenus(){
        return getSqlSessionTemplate().selectList(getStatement("getRoleMenus"));
    }

}
