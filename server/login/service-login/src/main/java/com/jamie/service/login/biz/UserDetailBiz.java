package com.jamie.service.login.biz;

import com.jamie.service.login.dao.RoleDao;
import com.jamie.service.login.dao.UserDao;
import com.jamie.service.login.entity.RoleEntity;
import com.jamie.service.login.entity.UserEntity;
import com.jamie.service.login.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Spring Security获取用户信息
 */
@Service
public class UserDetailBiz implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * Spring Security保存当前登陆用户的用户信息以及角色信息
     * @param userName：当前登录用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        // 先根据用户名获取用户信息
        UserVo userVo = new UserVo();
        userVo.setAccount(userName);
        UserEntity userEntity = userDao.getUserBy(userVo);

        if (userEntity == null){
            throw new UsernameNotFoundException(userName + ", 该用户不存在");
        }

        // 根据用户id查询其所有角色
        userVo.setId(userEntity.getId());
        List<RoleEntity> roles = roleDao.getRolesByUser(userVo);

        // Spring Security存放用户角色的地方
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEntity role: roles){
            GrantedAuthority authority = new SimpleGrantedAuthority(role.getRole());
            authorities.add(authority);
        }

        // 这个User是Spring Security自带的User，用于将存放当前登录用户的用户信息以及角色信息
        return new User(userEntity.getAccount(), userEntity.getPassword(), authorities);
    }

}
