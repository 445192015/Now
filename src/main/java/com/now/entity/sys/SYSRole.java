package com.now.entity.sys;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

/**
 * 角色
 * Created by Hujh on 17/8/28.
 */
@TableName("sys_role")
public class SYSRole extends Model<SYSRole>{

    @Id
    @GeneratedValue
    private Integer roleId;

    private String roleName;

    private List<SYSUser> users;

    @Override
    protected Serializable pkVal() {
        return roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<SYSUser> getUsers() {
        return users;
    }

    public void setUsers(List<SYSUser> users) {
        this.users = users;
    }
}
