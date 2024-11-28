package com.codemaster.service.impl;

import com.codemaster.entity.Role;
import com.codemaster.dao.RoleMapper;
import com.codemaster.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
