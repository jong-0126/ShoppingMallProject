package com.project.project.controller;

import com.project.project.domain.entity.User;
import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/registerPro")
    @ResponseBody
    public String registerPro(@ModelAttribute User user, RedirectAttributes redirectAttributes){

        try{
            userService.register(user);
            redirectAttributes.addFlashAttribute("message", "회원가입 성공!");
            return "redirect:/login";

        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "회원가입 실패!" + e.getMessage());
            return "redirect:/register";
        }
    }

    @PostMapping("/loginPro")
    @ResponseBody
    public String loginPro(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password, RedirectAttributes redirectAttributes) {
        if(userService.login(email, password)){
            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
            return "redirect:/main";
        }else{
            redirectAttributes.addFlashAttribute("message", "로그인 실패, 사용자 이름 및 비밀번호가 올바르지 않습니다.");
            return "redirect:/login";
        }
    }
}
