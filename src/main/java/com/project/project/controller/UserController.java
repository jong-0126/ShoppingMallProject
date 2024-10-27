package com.project.project.controller;

import com.project.project.domain.entity.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String registerForm(){
        return "register";
    }

    @GetMapping("/login")
    public String loginForm(){
        return "login";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(@RequestParam User user){
        try{
            userService.register(user);
            return "회원가입 성공!";

        }catch (Exception e){
            return "회원가입 실패!" + e.getMessage();
        }

    }

    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam String name, @RequestParam String password) {
        if(userService.login(name, password)){
            return "로그인 성공!";
        }else{
            return "로그인 실패, 사용자 이름 및 비밀번호가 올바르지 않습니다.";
        }
    }
}
