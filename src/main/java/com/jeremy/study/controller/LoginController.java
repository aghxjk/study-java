package com.jeremy.study.controller;

import com.jeremy.study.pojo.User;
import com.jeremy.study.result.Result;
import com.jeremy.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

@Controller
public class LoginController {

    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String username = HtmlUtils.htmlEscape(requestUser.getUsername());

        User user = userService.get(username, requestUser.getPassword());
        if ( user == null) {
            String message = "";
            return new Result(400);
        } else {
            return new Result(200);
        }
    }

//    set方法注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
