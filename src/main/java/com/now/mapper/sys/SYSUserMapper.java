package com.now.mapper.sys;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.now.entity.sys.SYSUser;

import java.util.List;

/**
 * Created by Hujh on 2017/7/7.
 */
public interface SYSUserMapper extends BaseMapper<SYSUser>{

    List<SYSUser> findAll();

}
