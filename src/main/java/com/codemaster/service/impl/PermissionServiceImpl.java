package com.codemaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.codemaster.dao.UserMapper;
import com.codemaster.entity.Permission;
import com.codemaster.dao.PermissionMapper;
import com.codemaster.entity.User;
import com.codemaster.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.codemaster.utils.MenuTree;
import com.codemaster.vo.query.PermissionQueryVo;
import com.codemaster.vo.query.RolePermissionVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.*;


@Service
@Transactional
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<Permission> findPermissionListByUserId(Long userId) {
        return baseMapper.findPermissionListByUserId(userId);
    }

    @Override
    public List<Permission> findPermissionList(PermissionQueryVo permissionQueryVo) {
        //创建条件构造器对象
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
//排序
        queryWrapper.orderByAsc("order_num");
//调用查询菜单列表的方法
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);
//生成菜单树
        List<Permission> menuTree = MenuTree.makeMenuTree(permissionList, 0L);
//返回数据
        return menuTree;
    }

    @Override
    public List<Permission> findParentPermissionList() {
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
//只查询type为目录和菜单的数据(type=0或type=1)
        queryWrapper.in("type", Arrays.asList(0,1));
//排序
        queryWrapper.orderByAsc("order_num");
//查询菜单数据
        List<Permission> permissionList = baseMapper.selectList(queryWrapper);
//构造顶级菜单信息，如果数据库中的菜单表没有数据，选择上级菜单时则显示顶级菜单
        Permission permission = new Permission();
        permission.setId(0L);
        permission.setParentId(-1L);
        permission.setLabel("顶级菜单");
        permissionList.add(permission);//将顶级菜单添加到集合
//生成菜单数据
        List<Permission> menuTree = MenuTree.makeMenuTree(permissionList, -1L);
//返回数据
        return menuTree;
    }

    @Override
    public boolean hasChildrenOfPermission(Long id) {
        //创建条件构造器对象
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<Permission>();
//查询父级ID
        queryWrapper.eq("parent_id", id);
//判断数量是否大于0，如果大于0则表示存在
        if(baseMapper.selectCount(queryWrapper)>0){
            return true;
        }
        return false;
    }

    @Override
    public RolePermissionVo findPermissionTree(Long userId, Long roleId) {
        //1.查询当前用户信息
        User user = userMapper.selectById(userId);
        List<Permission> list = null;
//2.判断当前用户角色，如果是管理员，则查询所有权限；如果不是管理员，则只查询自己所拥有的的权限
        if(!ObjectUtils.isEmpty(user.getIsAdmin()) && user.getIsAdmin() == 1){
//查询所有权限
            list = baseMapper.selectList(null);
        }else{
//根据用户ID查询
            list = baseMapper.findPermissionListByUserId(userId);
        }
//3.组装成树数据
        List<Permission> permissionList = MenuTree.makeMenuTree(list, 0L);
//4.查询要分配角色的原有权限
        List<Permission> rolePermissions =
                baseMapper.findPermissionListByRoleId(roleId);
//5.找出该角色存在的数据
        List<Long> listIds = new ArrayList<Long>();
        Optional.ofNullable(list).orElse(new ArrayList<>())
                .stream()
                .filter(Objects::nonNull) //等同于 obj -> obj!=null
                .forEach(item -> {
                    Optional.ofNullable(rolePermissions).orElse(new ArrayList<>())
                            .stream()
                            .filter(Objects::nonNull)
                            .forEach(obj ->{
                                if(item.getId().equals(obj.getId())){
                                    listIds.add(obj.getId());
                                    return;
                                }
                            });
                });
//创建
        RolePermissionVo vo = new RolePermissionVo();
        vo.setPermissionList(permissionList);
        vo.setCheckList(listIds.toArray());
        return vo;
    }


}
