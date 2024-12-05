package com.codemaster.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * packageName com.codemaster.entity
 *
 * @author lyf
 * @version JDK 1.8
 * @className UserInfo
 * @date 2024/11/29  11:18
 * @description 登录用户信息类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo implements Serializable {
    private Long id;//用户ID
    private String name;//用户名称
    private String avatar;//头像
    private String introduction;//介绍
    private Object[] roles;//角色权限集合
}