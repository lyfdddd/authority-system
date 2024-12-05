package com.codemaster.service;

import com.codemaster.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
