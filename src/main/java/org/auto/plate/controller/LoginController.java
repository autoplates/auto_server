package org.auto.plate.controller;

import org.auto.plate.entity.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class LoginController {

    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("上未登录，请登录！！！");
    }
}
