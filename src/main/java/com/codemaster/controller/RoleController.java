package com.codemaster.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codemaster.dto.RolePermissionDTO;
import com.codemaster.entity.Role;
import com.codemaster.service.PermissionService;
import com.codemaster.service.RoleService;
import com.codemaster.utils.Result;
import com.codemaster.vo.query.RolePermissionVo;
import com.codemaster.vo.query.RoleQueryVo;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;


@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Resource
    private RoleService roleService;
    @Resource
    private PermissionService permissionService;
    /**
     * 分页查询角色列表
     * @param roleQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(RoleQueryVo roleQueryVo){
        //创建分页对象
        IPage<Role> page = new Page<Role>(roleQueryVo.getPageNo(),roleQueryVo.getPageSize());
        //调用分页查询方法
        roleService.findRoleListByUserId(page,roleQueryVo);
        //返回数据
        return Result.ok(page);
    }
    /**
     * @description: 新增角色
     * @author: lyf
     * @date: 2024/12/9 9:07
     * @param: [role]
     * @return: com.codemaster.utils.Result
     **/
    @PostMapping("/add")
    public Result add(@RequestBody Role role){
        if(roleService.save(role)){
            return Result.ok().message("角色添加成功");
        }
        return Result.error().message("角色添加失败");
    }

    /**
     * 修改角色
     * @param role
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody Role role){
        if(roleService.updateById(role)){
            return Result.ok().message("角色修改成功");
        }
        return Result.error().message("角色修改失败");
    }

    /**
     * 删除角色
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public Result delete(@PathVariable Long id){
        if(roleService.removeById(id)){
            return Result.ok().message("角色删除成功");
        }
        return Result.error().message("角色删除失败");
        //todo 在删除角色前需要判断该角色是否被分配出去，如果被分配给用户使用，则提示无法删除，否则提示确认是否删除，确认删除的同时需要将sys_role_permission角色权限关系表对应的数据一起删除。
    }

    /**
     * @description: 分配权限查询权限树数据
     * @author: lyf
     * @date: 2024/12/9 9:57
     * @param: [userId, roleId]
     * @return: com.codemaster.utils.Result
     **/
    @GetMapping("/getAssignPermissionTree")
    public Result getAssignPermissionTree(Long userId, Long roleId) {
        //调用查询权限树数据的方法
        RolePermissionVo permissionTree = permissionService.findPermissionTree(userId, roleId);
        //返回数据
        return Result.ok(permissionTree);
    }

    /**
     * 分配权限-保存权限数据
     *
     * @param rolePermissionDTO
     * @return
     */
    @PostMapping("/saveRoleAssign")
    public Result saveRoleAssign(@RequestBody RolePermissionDTO rolePermissionDTO) {
        if (roleService.saveRolePermission(rolePermissionDTO.getRoleId(),
                rolePermissionDTO.getList())) {
            return Result.ok().message("权限分配成功");
        } else {
            return Result.error().message("权限分配失败");
        }
    }
}

