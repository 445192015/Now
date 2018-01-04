package com.now.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.now.api.sys.ISysUserService;
import com.now.entity.sys.SysUser;
import com.now.mapper.sys.SysUserMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author now123
 * @since 2018-01-04
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
