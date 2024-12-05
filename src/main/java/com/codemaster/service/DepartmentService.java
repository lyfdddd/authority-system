package com.codemaster.service;

import com.codemaster.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;
import com.codemaster.vo.query.DepartmentQueryVo;

import java.util.List;


public interface DepartmentService extends IService<Department> {
    /**
     * 查询部门列表
     * @param departmentQueryVo
     * @return
     */
    List<Department> findDepartmentList(DepartmentQueryVo departmentQueryVo);

    /**
     * @description:查询上级部门列表
     * @author: lyf 
     * @date: 2024/12/5 16:04
     * @param: 
     * @return: 
     **/
    List<Department> findParentDepartment();
    /**
     * 判断部门下是否有子部门
     * @param id
     * @return
     */
    boolean hasChildrenOfDepartment(Long id);
    /**
     * 判断部门下是否存在用户
     * @param id
     * @return
     */
    boolean hasUserOfDepartment(Long id);
}
