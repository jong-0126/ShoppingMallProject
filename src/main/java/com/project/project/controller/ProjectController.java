package com.project.project.controller;

import com.project.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
public class ProjectController {

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String main(){
        return "main";
    }

    @GetMapping("/detail")
    public String detail(){
        return "detail";
    }

    @GetMapping("/cart")
    public String cart() {
        return "cart";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/add-product")
    public String itemUpload(){
        return "add-product";
    }

    @GetMapping("/product")
    public String product(){
        return "product";
    }

    @GetMapping("/notice")
    public String notice() {
        return "notice";
    }

}
