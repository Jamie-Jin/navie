package com.jamie.service.login.biz;

import com.jamie.service.login.dao.*;
import com.jamie.service.login.entity.*;
import com.jamie.service.login.vo.RoleVo;
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

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private MenuDao menuDao;

    /**
     * 用户与角色的关联关系
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

    /**
     * 菜单与角色的关联关系
     * @param role
     * @param menu
     * @return
     */
    public int createRoleMenu(String role, String menu){
        RoleVo roleVo = new RoleVo();
        roleVo.setRole(role);
        RoleEntity roleEntity = roleDao.getRole(roleVo);

        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setPath(menu);
        int menuRes = menuDao.insertMenu(menuEntity);
        Integer menuId = menuEntity.getId();    //菜单id(MyBatis主键回写)

        int result = 0;
        if (menuRes != 0){
            RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
            roleMenuEntity.setMenuId(menuId);
            roleMenuEntity.setRoleId(roleEntity.getId());
            result = roleMenuDao.insertRoleMenu(roleMenuEntity);
        }
        return result;
    }

}
