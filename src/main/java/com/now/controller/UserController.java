package com.now.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.now.core.handler.ResultHandle;
import com.now.entity.Result;
import com.now.entity.User;
import com.now.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
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
     * 分页
     * @return
     */
    @GetMapping("/selectPage/{current}/{size}")
    public Page<User> selectPage(@PathVariable(value = "current") Integer current,
                                 @PathVariable(value = "size") Integer size) {
        System.err.println(current);
        System.err.println(size);
        return userService.selectPage(new Page<User>(current, size));
    }

    /**
     * 新增
     * @param user
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/save")
    public Result<User> save(@Valid User user, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ResultHandle.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 100; i++) {
            User _user = new User();
            _user.setName("Name" + i);
            users.add(_user);
        }
        userService.insertBatch(users);

        return ResultHandle.success(users);
    }

    /**
     * 删除一个用户
     * @param id
     */
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.deleteById(id);
    }

}
