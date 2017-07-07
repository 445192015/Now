package com.now.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.now.entity.User;

import java.util.List;

/**
 * Created by Hujh on 2017/7/7.
 */
public interface UserMapper extends BaseMapper<User>{

    List<User> findAll();

}
