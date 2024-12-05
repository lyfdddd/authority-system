package com.codemaster.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * packageName com.codemaster.vo
 *
 * @author lyf
 * @version JDK 1.8
 * @className TokenVo
 * @date 2024/11/29  11:05
 * @description TokenVo，用于保存token信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenVo {
    //过期时间
    private Long expireTime;
    //token
    private String token;
}