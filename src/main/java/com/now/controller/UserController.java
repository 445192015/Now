package com.now.controller;

import com.now.domain.Result;
import com.now.domain.User;
import com.now.repository.UserRepository;
import com.now.service.UserServiceImpl;
import com.now.core.handler.ResultHandle;
import com.now.service.impl.UserService;
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

    /**
     * 查询所有用户
     * @return
     */
    @GetMapping(value="/")
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/{id}")
    public User findOne(@PathVariable("id") Integer id) {
        return userService.findOne(id);
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/username/{username}")
    public List<User> findOne(@PathVariable("username") String username) {
        return userService.findByUsername(username);
    }

    /**
     * 根据id
     * @return
     */
    @GetMapping(value="/age/{id}")
    public void findAge(@PathVariable("id") Integer id) throws Exception {
        userService.findAge(id);
    }


    /**
     * 新增用户
     * @param user
     * @return
     */
    @PostMapping(value = "/save")
    public Result<User> save(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ResultHandle.error(1, bindingResult.getFieldError().getDefaultMessage());
        }
        return ResultHandle.success(user);
    }

    /**
     * 删除一个用户
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }


}
