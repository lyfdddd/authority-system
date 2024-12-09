package com.codemaster.vo.query;

import com.codemaster.entity.Permission;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * packageName com.codemaster.vo.query
 *
 * @author lyf
 * @version JDK 1.8
 * @className RolePermissionVo
 * @date 2024/12/9  9:53
 * @description TODO
 */
@Data
public class RolePermissionVo {
    /**
     * 菜单数据
     */
    private List<Permission> permissionList = new ArrayList<Permission>();
    /**
     * 该角色原有分配的菜单数据
     */
    private Object [] checkList;
}