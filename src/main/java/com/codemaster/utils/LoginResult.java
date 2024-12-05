package com.codemaster.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName com.codemaster.utils
 *
 * @author lyf
 * @version JDK 1.8
 * @className LoginResult
 * @date 2024/11/29  9:00
 * @description TODO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResult {
    //用户编号
    private Long id;
    //状态码
    private int code;
    //token令牌
    private String token;
    //token过期时间
    private Long expireTime;
}