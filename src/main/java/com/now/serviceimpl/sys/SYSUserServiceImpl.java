package com.now.serviceimpl.sys;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.now.entity.sys.SYSUser;
import com.now.mapper.sys.SYSUserMapper;
import com.now.service.sys.SYSUserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hujh on 2017/5/2.
 */
@Service
public class SYSUserServiceImpl extends ServiceImpl<SYSUserMapper, SYSUser> implements SYSUserService {


    @Override
    public List<SYSUser> findAll() {
        return baseMapper.findAll();
    }
}
