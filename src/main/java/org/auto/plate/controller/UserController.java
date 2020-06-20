package org.auto.plate.controller;

import org.auto.plate.entity.RespBean;
import org.auto.plate.entity.User;
import org.auto.plate.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * (User)表控制层
 *
 * @author makejava
 * @since 2020-04-09 23:32:15
 */
@RestController
@RequestMapping("/api")
public class UserController {
    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("user")
    public User selectOne(Integer id) {
        return this.userService.queryById(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RespBean register(@RequestBody User user) {
        return this.userService.insert(user);
    }

    @GetMapping("/userlist")
    public RespBean getUserList() {
        return userService.getUserList();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespBean login(@RequestBody User user) {
        return userService.doLogin(user);
    }
}