package com.now.controller.sys;

import com.baomidou.mybatisplus.plugins.Page;
import com.now.core.handler.ResultHandle;
import com.now.entity.sys.SYSUser;
import com.now.entity.cm.Result;
import com.now.service.sys.SYSUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
@RequestMapping(value = "/sys/user")
public class SYSUserController {

    private static final Logger logger = LoggerFactory.getLogger(SYSUserController.class);

    @Autowired
    private SYSUserService userService;

    @GetMapping("/test")
    public void test() {
        logger.info("adasdadsad");
    }


    /**
     * 分页
     * @return
     */
    @GetMapping("/selectPage/{current}/{size}")
    public Page<SYSUser> selectPage(@PathVariable(value = "current") Integer current,
                                    @PathVariable(value = "size") Integer size) {
        return userService.selectPage(new Page<SYSUser>(current, size));
    }

    /**
     * 新增
     * @param SYSUser
     * @param bindingResult
     * @return
     */
    @PostMapping(value = "/save")
    public Result<SYSUser> save(@Valid SYSUser SYSUser, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            ResultHandle.error(1, bindingResult.getFieldError().getDefaultMessage());
        }

        List<SYSUser> SYSUsers = new ArrayList<SYSUser>();
        for (int i = 0; i < 100; i++) {
            SYSUser sysUser = new SYSUser();
            sysUser.setUsername("name" + i);
            SYSUsers.add(sysUser);
        }
        userService.insertBatch(SYSUsers);

        return ResultHandle.success(SYSUsers);
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
