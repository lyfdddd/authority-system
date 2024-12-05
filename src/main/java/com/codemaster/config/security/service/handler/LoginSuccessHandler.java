package com.codemaster.config.security.service.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codemaster.config.redis.RedisService;
import com.codemaster.entity.User;
import com.codemaster.utils.JwtUtils;
import com.codemaster.utils.LoginResult;
import com.codemaster.utils.ResultCode;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * packageName com.codemaster.config.security.service.handler
 *
 * @author lyf
 * @version JDK 1.8
 * @className LoginSuccessHandler
 * @date 2024/11/28  16:57
 * @description 登录认证成功处理器类
 */
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    private JwtUtils jwtUtils;
    @Resource
    private RedisService redisService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //设置客户端的响应的内容类型
        response.setContentType("application/json;charset=UTF-8");
        //获取当登录用户信息
        User user = (User) authentication.getPrincipal();
        //生成token
        String token = jwtUtils.generateToken(user);
        //设置token签名密钥以及过期时间
        long expireTime = Jwts.parser()//获取DefaultJwtParser对象
                .setSigningKey(jwtUtils.getSecret())//设置签名的密钥
                .parseClaimsJws(token.replace("jwt_", ""))
                .getBody().getExpiration().getTime();//获取token过期时间
        //创建登录结果对象
        LoginResult loginResult = new LoginResult(user.getId(), ResultCode.SUCCESS, token, expireTime);
        //将结果转换为JSON字符串并返回
        String result = JSON.toJSONString(loginResult,SerializerFeature.DisableCircularReferenceDetect);
        //获取输出流
        ServletOutputStream outputStream = response.getOutputStream();
        outputStream.write(result.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();
        //把生成的token存到redis
        String tokenKey = "token_" + token;
        redisService.set(tokenKey, token,jwtUtils.getExpiration() / 1000);
    }
}