package com.codemaster.vo.query;

import com.codemaster.entity.Role;
import lombok.Data;

/**
 * packageName com.codemaster.vo.query
 *
 * @author lyf
 * @version JDK 1.8
 * @className RoleQueryVo
 * @date 2024/12/9  9:01
 * @description RoleQueryVo查询条件类
 */
@Data
public class RoleQueryVo extends Role {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
    private Long userId;//用户ID
}