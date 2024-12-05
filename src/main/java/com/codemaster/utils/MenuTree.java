package com.codemaster.utils;

import com.codemaster.entity.Permission;
import com.codemaster.vo.RouterVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * packageName com.codemaster.utils
 *
 * @author lyf
 * @version JDK 1.8
 * @className MenuTree
 * @date 2024/11/29  13:35
 * @description 菜单树工具类 生成菜单树
 */
public class MenuTree {
    /**
     * @description: 生成路由
     * @author: lyf
     * @date: 2024/11/29 13:38
     * @param: [menuList 菜单列表, pid 父级菜单]
     * @return: java.util.List<com.codemaster.vo.RouterVo>
     **/
    public static List<RouterVo> makeRouter(List<Permission> menuList,Long pid) {
        //创建集合保存路由列表
        List<RouterVo> routers = new ArrayList<>();
        //如果menuList菜单列表不为空，则使用菜单列表，否则创建集合对象
        Optional.ofNullable(menuList).orElse(new ArrayList<Permission>())
                //筛选不为空的菜单及菜单父id相同的数据
                .stream().filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    //创建路由并赋值
                    RouterVo router = new RouterVo();
                    router.setPath(item.getUrl());//路由地址
                    router.setName(item.getName());//路由名称
                    //判断是否是一级菜单
                    if (item.getParentId() == 0L) {
                        router.setComponent("Layout");//一级菜单组件
                        router.setAlwaysShow(true);//显示路由
                    } else {
                        router.setComponent(item.getUrl());//具体的组件
                        router.setAlwaysShow(false);//折叠路由
                    }
                    //设置meta信息
                    router.setMeta(router.new Meta(item.getLabel(), item.getIcon(), item.getCode().split(",")));
                    //递归生成路由
                    List<RouterVo> children = makeRouter(menuList, item.getId());
                    router.setChildren(children);
                    //将路由添加到集合中
                    routers.add(router);
                });
        return routers;
    }

    /**
     * @description: 生成菜单树
     * @author: lyf
     * @date: 2024/11/29 13:54
     * @param: [menuList, pid]
     * @return: java.util.List<com.codemaster.entity.Permission>
     **/
    public static List<Permission> makeMenuTree(List<Permission> menuList,Long pid) {
        //创建集合保存菜单
        List<Permission> permissionList = new ArrayList<>();
        //如果menuList菜单列表不为空，则使用菜单列表，否则创建集合对象
        Optional.ofNullable(permissionList).orElse(new ArrayList<>())
                .stream().filter(item -> item != null && item.getParentId() == pid)
                .forEach(item -> {
                    //创建菜单权限对象
                    Permission permission = new Permission();
                    //复制属性
                    BeanUtils.copyProperties(item,permission);
                    //获取每一个item的下级菜单，递归生成菜单树
                    List<Permission> children = makeMenuTree(menuList, item.getId());
                    permission.setChildren(children);
                    permissionList.add(permission);
                });
        return permissionList;
    }

}