package com.codemaster.dto;

import lombok.Data;
import java.util.List;
/**
 * packageName com.codemaster.dto
 *
 * @author lyf
 * @version JDK 1.8
 * @className UserRoleDTO
 * @date 2024/12/9  14:34
 * @description 用于给用户分配角色时保存选中的角色数据
 */
@Data
public class UserRoleDTO {
    private Long userId;
    private List<Long> roleIds;
}