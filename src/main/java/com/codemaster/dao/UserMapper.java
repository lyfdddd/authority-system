package com.codemaster.dao;

import com.codemaster.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 删除用户角色关系
     * @param userId
     * @return
     */
    @Delete("delete from sys_user_role where user_id=#{userId}")
    int deleteUserRole(Long userId);

    /**
     * 保存用户角色关系
     * @param userId
     * @param roleIds
     * @return
     */
    int saveUserRole(@Param("userId") Long userId, @Param("roleIds")List<Long> roleIds);
}
