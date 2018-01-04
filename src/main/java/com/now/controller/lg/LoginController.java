package com.now.controller.lg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Hujh on 17/8/29.
 */
@RestController
public class LoginController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/test1")
    public String test1(HttpServletRequest request) {
        Object object = redisTemplate.opsForHash().keys("spring:session:sessions:04f12bf4-5fb1-4602-8ca1-4b09a6c5827f");
        return request.getSession().getId() + "  " + object;
    }

    @GetMapping("/test2")
    public String test2(HttpServletRequest request) {
        return request.getSession().getId();
    }



}
