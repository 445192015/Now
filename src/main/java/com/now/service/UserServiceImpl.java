package com.now.service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.now.entity.User;
import com.now.mapper.UserMapper;
import com.now.service.impl.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hujh on 2017/5/2.
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{


    @Override
    public List<User> findAll() {
        return baseMapper.findAll();
    }
}
