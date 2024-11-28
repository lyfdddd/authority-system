package com.codemaster.service.impl;

import com.codemaster.entity.Department;
import com.codemaster.dao.DepartmentMapper;
import com.codemaster.service.DepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
