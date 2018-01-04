package com.now.controller.sys;


import com.baomidou.mybatisplus.plugins.Page;
import com.now.api.sys.ISysUserService;
import com.now.core.handler.ResultHandle;
import com.now.entity.cm.Result;
import com.now.entity.sys.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author now
 * @since 2018-01-04
 */
@RestController
@RequestMapping("/sysUser")
public class SysUserController {

    @Autowired
    private ISysUserService iSysUserService;

    /**
     * 分页查询
     * size：每页大小
     * current：当前页码
     * @param page
     * @return
     */
    @GetMapping("/page")
    public Result<Page<SysUser>> page(Page page) {
        return ResultHandle.success(iSysUserService.selectPage(page));
    }

    /**
     * 新增
     * @param sysUser
     * @return
     */
    @PostMapping("/insert")
    public Result insert(SysUser sysUser) {
        iSysUserService.insert(sysUser);
        return ResultHandle.success();
    }

}

