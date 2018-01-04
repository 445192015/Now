package com.now.entity.sys;

import java.io.Serializable;

/**
 *
 * @author now
 * @since 2018-01-04
 */
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;
    /**
     * 用户编码
     */
    private String usercode;
    /**
     * 用户名称
     */
    private String username;
    /**
     * 密码
     */
    private String password;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsercode() {
        return usercode;
    }

    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "SysUser{" +
        ", id=" + id +
        ", usercode=" + usercode +
        ", username=" + username +
        ", password=" + password +
        "}";
    }
}
