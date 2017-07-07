package com.now.service;

import com.now.domain.User;
import com.now.core.enums.ResultEnums;
import com.now.core.exception.NowException;
import com.now.repository.UserRepository;
import com.now.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hujh on 2017/5/2.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public void findAge(Integer id) throws Exception {
        User user = userRepository.findOne(id);
        Integer age = user.getAge();
        if(age < 10) {
            //年龄小于10
            throw new NowException(ResultEnums.ERROR_10);
        } else {
            //年龄大于10
            throw new NowException(ResultEnums.ERROR_11);
        }

    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public List<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Integer id) {
        userRepository.delete(id);
    }
}
