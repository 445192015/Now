package com.now.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by Administrator on 2017/12/12.
 */
@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

}
