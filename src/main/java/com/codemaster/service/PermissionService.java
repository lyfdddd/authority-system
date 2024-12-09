package com.codemaster.service;

import com.codemaster.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codemaster.vo.query.PermissionQueryVo;
import com.codemaster.vo.query.RolePermissionVo;

import java.util.List;


public interface PermissionService extends IService<Permission> {
    /**
     * @description: 根据用户id查询权限列表
     * @author: lyf
     * @date: 2024/11/29 9:57
     * @param: [userId]
     * @return: java.util.List<com.codemaster.entity.Permission>
     **/
    List<Permission> findPermissionListByUserId(Long userId);

    /**
     * 查询菜单列表
     * @param permissionQueryVo
     * @return
     */
    List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo);
    
    /**
     * @description: 查询上级菜单列表
     * @author: lyf 
     * @date: 2024/12/6 10:01
     * @param: []
     * @return: java.util.List<com.codemaster.entity.Permission>
     **/
    List<Permission> findParentPermissionList();
    /**
     * 检查菜单是否有子菜单
     * @param id
     * @return
     */
    boolean hasChildrenOfPermission(Long id);

    /**
     * 查询分配权限树列表
     * @param userId
     * @param roleId
     * @return
     */
    RolePermissionVo findPermissionTree(Long userId, Long roleId);

}
