package com.codemaster.config.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * packageName com.codemaster.config.security.exception
 *
 * @author lyf
 * @version JDK 1.8
 * @className CustomerAuthenticationException
 * @date 2024/11/29  10:32
 * @description 自定义异常
 */
public class CustomerAuthenticationException extends AuthenticationException {
    public CustomerAuthenticationException(String msg) {
        super(msg);
    }
}