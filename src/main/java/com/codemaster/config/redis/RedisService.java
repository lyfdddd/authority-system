package com.codemaster.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * packageName com.codemaster.config.redis
 *
 * @author lyf
 * @version JDK 1.8
 * @className RedisService
 * @date 2024/11/29  10:15
 * @description redis业务工具类
 */
@Component
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    //存缓存
    public void set(String key ,String value,Long timeOut){
        redisTemplate.opsForValue().set(key,value,timeOut, TimeUnit.SECONDS);
    }
    //取缓存
    public String get(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    //清除缓存
    public void del(String key){
        redisTemplate.delete(key);
    }
}