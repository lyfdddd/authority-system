package com.codemaster.config.security.service;

import com.codemaster.entity.Permission;
import com.codemaster.entity.User;
import com.codemaster.service.PermissionService;
import com.codemaster.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * packageName com.codemaster.config.security.service
 *
 * @author lyf
 * @version JDK 1.8
 * @className CustomerUserDetailsService
 * @date 2024/11/28  16:52
 * @description TODO
 */
@Service
public class CustomerUserDetailsService implements UserDetailsService {
    @Resource
    private UserService userService;
    @Resource
    private PermissionService permissionService;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findUserByUserName(username);
        if (Objects.isNull(user)) {
            throw new RuntimeException("用户名或密码错误");
        }
        //查询用户拥有的权限列表
        List<Permission> permissionListByUserId = permissionService.findPermissionListByUserId(user.getId());
        //获取权限编码
        List<String> collect = permissionListByUserId.stream().filter(item -> item != null).map(item -> item.getCode()).filter(item -> item != null).collect(Collectors.toList());
        //转换成数组
        String[] collectArray = collect.toArray(new String[collect.size()]);
        //设置权限列表
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(collectArray);
        //设置菜单列表
        user.setAuthorities(authorityList);
        user.setPermissionList(permissionListByUserId);
        return user;
    }
}