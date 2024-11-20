package com.project.project.controller;

import com.project.project.domain.entity.User;
import com.project.project.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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
    public String registerPro(@ModelAttribute User user, Model model){

        if(userService.isEmailDuplicated(user.getEmail())){
            model.addAttribute("message", "이미 등록된 이메일입니다.");
            model.addAttribute("searchUrl", "/register");
            return "message";
        }

        try{
            userService.register(user);
            model.addAttribute("message", "회원가입 성공");
            model.addAttribute("searchUrl", "/login");
            return "message";

        }catch (Exception e) {
            model.addAttribute("message", "회원가입 실패!");
            model.addAttribute("searchUrl", "/register");
            return "message";
        }
    }

    @PostMapping("/loginPro")
    public String loginPro(@RequestParam(name = "email") String email,
                           @RequestParam(name = "password") String password,
                           Model model, HttpSession session) {

        System.out.println("Login attempt for email: " + email); // 로그 추가

        // 사용자 인증
        Optional<User> authenticateUserOptional = userService.authenticate(email, password);

        if (authenticateUserOptional.isPresent()) {
            User authenticateUser = authenticateUserOptional.get(); // 인증된 사용자 정보 가져오기
            session.setAttribute("user_id", authenticateUser.getUser_id());
            session.setAttribute("email", email); // 세션에 사용자 정보 저장
            session.setAttribute("isSuperAdmin", authenticateUser.getIsSuperAdmin());
            model.addAttribute("message", "로그인 성공");
            model.addAttribute("searchUrl", "/main");
            return "message"; // 로그인 성공 시 메시지 페이지로 이동
        } else {
            model.addAttribute("message", "로그인 실패, 이메일 및 비밀번호가 올바르지 않습니다.");
            model.addAttribute("searchUrl", "/login");
            return "message"; // 로그인 실패 시 메시지 페이지로 이동
        }
    }

    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 무효화 (로그아웃)
        return "redirect:/main"; // 로그아웃 후 로그인 페이지로 리다이렉트
    }
}
