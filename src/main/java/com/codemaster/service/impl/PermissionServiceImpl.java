package com.codemaster.service.impl;

import com.codemaster.entity.Permission;
import com.codemaster.dao.PermissionMapper;
import com.codemaster.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
