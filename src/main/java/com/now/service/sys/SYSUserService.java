package com.now.service.sys;

import com.baomidou.mybatisplus.service.IService;
import com.now.entity.sys.SYSUser;

import java.util.List;

/**
 * Created by Hujh on 2017/7/7.
 */
public interface SYSUserService extends IService<SYSUser> {

    List<SYSUser> findAll();

}
