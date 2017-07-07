package com.now.repository;

import com.now.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Hujh on 2017/5/2.
 */
public interface UserRepository extends JpaRepository<User, Integer>{

    //根据username查询
    public List<User> findByUsername(String username);
}
