package com.project.project.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) { // 세션에 사용자 정보가 없으면
            response.sendRedirect("/login"); // 로그인 페이지로 리다이렉트
            return false; // 요청 중단
        }
        return true; // 세션에 유저 정보가 있으면 요청 진행

    }
}
