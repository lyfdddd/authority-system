package com.codemaster.vo.query;

import com.codemaster.entity.User;
import lombok.Data;

/**
 * packageName com.codemaster.vo.query
 *
 * @author lyf
 * @version JDK 1.8
 * @className UserQueryVo
 * @date 2024/12/9  10:29
 * @description TODO
 */
@Data
public class UserQueryVo extends User {
    private Long pageNo = 1L;//当前页码
    private Long pageSize = 10L;//每页显示数量
}