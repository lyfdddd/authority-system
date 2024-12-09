package com.codemaster.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * packageName com.codemaster.config
 *
 * @author lyf
 * @version JDK 1.8
 * @className OssProperties
 * @date 2024/12/9  14:01
 * @description 阿里云oss存储属性类
 */
@Data
@Component
@ConfigurationProperties(prefix = "aliyun.oss.file")
public class OssProperties {
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;
    private String endpoint;
}