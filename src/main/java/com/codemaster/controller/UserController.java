package com.codemaster.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.codemaster.dto.UserRoleDTO;
import com.codemaster.entity.Role;
import com.codemaster.entity.User;
import com.codemaster.service.RoleService;
import com.codemaster.service.UserService;
import com.codemaster.utils.Result;
import com.codemaster.vo.query.RoleQueryVo;
import com.codemaster.vo.query.UserQueryVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Resource
    private RoleService roleService;

    /**
     * 查询用户列表
     *
     * @param userQueryVo
     * @return
     */
    @GetMapping("/list")
    public Result list(UserQueryVo userQueryVo) {
        //创建分页信息
        IPage<User> page = new Page<User>(userQueryVo.getPageNo(),
                userQueryVo.getPageSize());
        //调用分页查询方法
        userService.findUserListByPage(page, userQueryVo);
        //返回数据
        return Result.ok(page);
    }

    /**
     * @description: 添加用户
     * @author: lyf
     * @date: 2024/12/9 14:16
     * @param: [user]
     * @return: com.codemaster.utils.Result
     **/
    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Result add(@RequestBody User user) {
        //查询用户
        User item = userService.findUserByUserName(user.getUsername());
        //判断对象是否为空
        if (item != null) {
            return Result.error().message("该登录名称已被使用，请重新输入！");
        }
        //密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //调用保存用户信息的方法
        if(userService.save(user)){
            return Result.ok().message("用户添加成功");
        }
        return Result.error().message("用户添加失败");
    }

    /**
     * 修改用户
     *
     * @param user
     * @return
     */
    @PutMapping("/update")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    public Result update(@RequestBody User user) {
//查询用户
        User item = userService.findUserByUserName(user.getUsername());
//判断对象是否为空,且查询到的用户ID不等于当前编辑的用户ID，表示该名称被占用
        if (item != null && item.getId() != user.getId()) {
            return Result.error().message("登录名称已被占用！");
        }
//调用修改用户信息的方法
        if(userService.updateById(user)){
            return Result.ok().message("用户修改成功");
        }
        return Result.error().message("用户修改失败");
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public Result delete(@PathVariable Long id) {
//调用删除用户信息的方法
        if(userService.deleteById(id)){
            return Result.ok().message("用户删除成功");
        }
        return Result.error().message("用户删除失败");
    }

    /**
     * 获取分配角色列表
     * @param roleQueryVo
     * @return
     */
    @GetMapping("/getRoleListForAssign")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result getRoleListForAssign(RoleQueryVo roleQueryVo){
//创建分页对象
        IPage<Role> page = new Page<Role>(roleQueryVo.getPageNo(),
                roleQueryVo.getPageSize());
//调用查询方法
        roleService.findRoleListByUserId(page,roleQueryVo);
//返回数据
        return Result.ok(page);
    }
    /**
     * 根据用户ID查询该用户拥有的角色列表
     * @param userId
     * @return
     */
    @GetMapping("/getRoleByUserId/{userId}")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result getRoleByUserId(@PathVariable Long userId){
//调用根据用户ID查询该用户拥有的角色ID的方法
        List<Long> roleIds = roleService.findRoleIdByUserId(userId);
        return Result.ok(roleIds);
    }

    /**
     * 分配角色
     * @param userRoleDTO
     * @return
     */
    @PostMapping("/saveUserRole")
    @PreAuthorize("hasAuthority('sys:user:assign')")
    public Result saveUserRole(@RequestBody UserRoleDTO userRoleDTO){
        if (userService.saveUserRole(userRoleDTO.getUserId(),
                userRoleDTO.getRoleIds())) {
            return Result.ok().message("角色分配成功");
        }
        return Result.error().message("角色分配失败");
    }

}


