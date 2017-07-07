package com.now.service;

import com.now.domain.User;
import com.now.core.enums.ResultEnums;
import com.now.core.exception.NowException;
import com.now.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Hujh on 2017/5/2.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void insert(List<User> users) {
        userRepository.save(users);
    }

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
}
