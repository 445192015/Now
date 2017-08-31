package com.now.entity.sys;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by Hujh on 17/8/29.
 */
@TableName("sys_permission")
public class SYSPermission extends Model<SYSPermission>{

    @Id
    @GeneratedValue
    private Integer id;

    private String permissionName;

    private SYSRole sysRole;

    @Override
    protected Serializable pkVal() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public SYSRole getSysRole() {
        return sysRole;
    }

    public void setSysRole(SYSRole sysRole) {
        this.sysRole = sysRole;
    }
}
