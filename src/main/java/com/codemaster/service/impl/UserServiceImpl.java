package com.codemaster.service.impl;

import com.codemaster.entity.User;
import com.codemaster.dao.UserMapper;
import com.codemaster.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
