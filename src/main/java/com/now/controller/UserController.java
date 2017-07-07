package com.now.controller;

import com.now.domain.Result;
import com.now.domain.User;
import com.now.repository.UserRepository;
import com.now.service.UserService;
import com.now.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户Controller
 * Created by Hujh on 2017/5/2.
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping(value="/findAll")
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/findOne/{id}")
    public User findOne(@PathVariable("id") Integer id) {
        return userRepository.findOne(id);
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/findByUsername/{username}")
    public List<User> findOne(@PathVariable("username") String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/findAge/{id}")
    public void findAge(@PathVariable("id") Integer id) throws Exception {
        userService.findAge(id);
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping(value = "/insert")
    public Result<User> insert(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ResultUtil.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultUtil.success(user);
    }

    /**
     * 更新
     * @param user
     * @return
     */
    @PutMapping(value = "/update")
    public User update(User user) {
        return userRepository.save(user);
    }

    /**
     * 删除一个用户
     * @param id
     */
    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userRepository.delete(id);
    }


}
