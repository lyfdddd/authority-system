package com.codemaster.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.codemaster.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codemaster.vo.query.RoleQueryVo;

import java.util.List;


public interface RoleService extends IService<Role> {
    /**
     * 根据用户查询角色列表
     * @param page
     * @param roleQueryVo
     * @return
     */
    IPage<Role> findRoleListByUserId(IPage<Role> page, RoleQueryVo roleQueryVo);

    /**
     * 保存角色权限关系
     * @param roleId
     * @param permissionIds
     * @return
     */
    boolean saveRolePermission(Long roleId, List<Long> permissionIds);

}
