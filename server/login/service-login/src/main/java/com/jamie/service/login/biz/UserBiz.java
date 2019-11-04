package com.jamie.service.login.biz;

import com.jamie.service.login.dao.RoleDao;
import com.jamie.service.login.dao.UserDao;
import com.jamie.service.login.dao.UserRoleDao;
import com.jamie.service.login.entity.RoleEntity;
import com.jamie.service.login.entity.UserEntity;
import com.jamie.service.login.entity.UserRoleEntity;
import com.jamie.service.login.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserBiz {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserRoleDao userRoleDao;

    /**
     * 创建用户
     * @param userVo
     * @return
     */
    public int createUser(UserVo userVo){
        // 用户
        UserEntity userEntity = new UserEntity();
        userEntity.setAccount(userVo.getAccount());
        userEntity.setName(userVo.getName());
        userEntity.setPassword(userVo.getPassword());
        userDao.insertUser(userEntity);
        int userId = userEntity.getId(); //MyBatis主键回写

        // 角色
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(userVo.getRole());
        roleDao.insertRole(roleEntity);
        int roleId = roleEntity.getId(); //MyBatis主键回写

        // 用户角色关联
        int result = 0;
        if (userId!=0 && roleId!=0){
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(roleId);
            result = userRoleDao.insertUserRole(userRoleEntity);
        }

        return result;
    }

}
