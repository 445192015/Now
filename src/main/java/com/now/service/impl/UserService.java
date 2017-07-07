package com.now.service.impl;

import com.now.domain.User;

import java.util.List;

/**
 * Created by Hujh on 2017/7/7.
 */
public interface UserService {

    void findAge(Integer id) throws Exception;

    List<User> findAll();

    User findOne(Integer id);

    List<User> findByUsername(String username);

    User save(User user);

    void delete(Integer id);
}
