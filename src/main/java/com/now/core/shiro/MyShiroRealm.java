package com.now.core.shiro;

import com.now.entity.sys.SYSUser;
import com.now.service.sys.SYSUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hujh on 17/8/29.
 */

public class MyShiroRealm extends AuthorizingRealm{

    @Autowired
    private SYSUserService sysUserService;

    private static final Logger logger = LoggerFactory.getLogger(MyShiroRealm.class);

    /**
     * 权限认证
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        logger.info("--------执行Shiro权限验证--------");
        String username = (String) super.getAvailablePrincipal(principals);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("username", username);
        List<SYSUser> users = sysUserService.selectByMap(map);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("admin");
        info.addStringPermission("manager");
        return info;
    }

    /**
     * 登录认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        if(username.equals("xxx")) {
            return new SimpleAuthenticationInfo(username, "xxx", getName());
        }
        return null;
    }
}
