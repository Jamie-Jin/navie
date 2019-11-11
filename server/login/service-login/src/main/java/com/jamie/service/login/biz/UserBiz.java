package com.jamie.service.login.biz;

import com.jamie.api.login.entity.*;
import com.jamie.api.login.vo.RoleVo;
import com.jamie.api.login.vo.UserVo;
import com.jamie.service.login.dao.*;
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
    private MenuBiz menuBiz;

    /**
     * 插入角色
     * @param userVo
     * @return
     */
    public int createRole(UserVo userVo){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRole(userVo.getRole());
        roleEntity.setRoleCn(userVo.getRoleCn());//角色中文名
        return roleDao.insertRole(roleEntity);
    }

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

        // 先检查角色是否存在
        RoleVo roleVo = new RoleVo();
        roleVo.setRole(userVo.getRole());
        RoleEntity roleEntity = roleDao.getRole(roleVo);

        // 若角色为空，插入一条数据
        if (roleEntity == null){
            // 角色
            roleEntity = new RoleEntity();
            roleEntity.setRole(userVo.getRole());
            roleDao.insertRole(roleEntity);
        }
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

        // 根据菜单路径获取菜单数据
        MenuEntity menuEntity = new MenuEntity();
        menuEntity.setPath(menu);
        MenuEntity result = menuBiz.getMenu(menuEntity);

        // 菜单不存在，新增
        if (null == result){
            menuBiz.insertMenu(menuEntity);
            Integer menuId = menuEntity.getId();    //菜单id(MyBatis主键回写)
            menuEntity.setId(menuId);
        }
        // 菜单已存在，不用新增
        else {
            menuEntity.setId(result.getId());
        }

        RoleMenuEntity roleMenuEntity = new RoleMenuEntity();
        roleMenuEntity.setMenuId(menuEntity.getId());
        roleMenuEntity.setRoleId(roleEntity.getId());
        return roleMenuDao.insertRoleMenu(roleMenuEntity);
    }

}
