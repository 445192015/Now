package com.now.controller.sys;

import com.now.entity.sys.SYSUser;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hujh on 17/8/29.
 */
@RestController
public class SYSLoginController {

    @PostMapping("/login")
    public String login(SYSUser sysUser) {
        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getUsername(), sysUser.getPassword());
        Subject subject = SecurityUtils.getSubject();
        subject.login(token);
        Boolean isAuthenticated = subject.isAuthenticated();
        return "login";
    }
}
