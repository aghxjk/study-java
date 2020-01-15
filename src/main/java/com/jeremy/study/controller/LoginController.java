package com.jeremy.study.controller;

import com.jeremy.study.pojo.User;
import com.jeremy.study.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.HtmlUtils;
import java.util.Objects;

@Controller
public class LoginController {

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result login(@RequestBody User requestUser) {

        String username = HtmlUtils.htmlEscape(requestUser.getUsername());

        if (!Objects.equals("admin", username) || !Objects.equals("123456", requestUser.getPassword())) {
            String message = "";
            System.out.println("test");
            return new Result(400);
        } else {
            return new Result(200);
        }
    }
}
