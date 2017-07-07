package com.now.service.impl;

import com.baomidou.mybatisplus.service.IService;
import com.now.entity.User;

import java.util.List;

/**
 * Created by Hujh on 2017/7/7.
 */
public interface UserService extends IService<User> {

    List<User> findAll();

}
