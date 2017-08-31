package com.now.entity.sys;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.management.relation.Role;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * 用户
 * Created by Hujh on 2017/5/2.
 */
@TableName(value = "sys_user")
public class SYSUser extends Model<SYSUser>{

    @Id
    @GeneratedValue
    private Integer userId;

    private String username;

    private String password;

    private List<Role> roles;

    @Override
    protected Serializable pkVal() {
        return userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
