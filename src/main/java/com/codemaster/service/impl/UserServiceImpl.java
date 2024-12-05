package com.codemaster.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.codemaster.entity.User;
import com.codemaster.dao.UserMapper;
import com.codemaster.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    /**
     * @description: 根据用户名查询用户信息
     * @author: lyf
     * @date: 2024/11/28 16:50
     * @param: [username]
     * @return: com.codemaster.entity.User
     **/
    @Override
    public User findUserByUserName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }
}
