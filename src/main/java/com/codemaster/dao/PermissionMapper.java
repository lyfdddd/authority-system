package com.codemaster.dao;

import com.codemaster.entity.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * @description: 根据用户id查询权限列表
     * @author: lyf
     * @date: 2024/11/29 9:48
     * @param: [userId]
     * @return: java.util.List<com.codemaster.entity.Permission>
     **/
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<Permission> findPermissionListByRoleId(Long roleId);
}
