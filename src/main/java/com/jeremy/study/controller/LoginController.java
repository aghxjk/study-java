package com.jeremy.study.controller;

import com.jeremy.study.pojo.User;
import com.jeremy.study.result.Result;
import com.jeremy.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;

/**
 * 1.为了完善登录功能，我们需要限制未登录状态下对核心功能页面的访问。登录拦截可以由多种方式来实现，我们首先讲解后端拦截器的开发。（注意如果没有把前后端项目整合起来，就没有办法使用这种方式）
 *      一个简单拦截器的逻辑如下：
 *      1) 用户访问 URL，检测是否为登录页面，如果是登录页面则不拦截
 *      2) 如果用户访问的不是登录页面，检测用户是否已登录，如果未登录则跳转到登录页面
 *
 * 2. 一种比较简单的实现方式
 *      为了保存登录状态，我们可以把用户信息存在 Session 对象中（当用户在应用程序的 Web 页之间跳转时，存储在 Session 对象中的变量不会丢失），这样在访问别的页面时，可以通过判断是否存在用户变量来判断用户是否登录。
 */

@Controller
public class LoginController {

    private UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser, HttpSession httpSession) {

        String username = HtmlUtils.htmlEscape(requestUser.getUsername());

        User user = userService.get(username, requestUser.getPassword());
        if ( user == null) {
            return new Result(400);
        } else {
            httpSession.setAttribute("user", user);
            return new Result(200);
        }
    }

//    set方法注入
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
