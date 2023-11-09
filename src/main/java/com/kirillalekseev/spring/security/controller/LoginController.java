package com.kirillalekseev.spring.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }
}
//
//    @PostMapping("/login")
//    public String processLogin(Authentication authentication) {
//        if(authentication  != null && authentication.isAuthenticated()){
//            return "redirect:/";
//        }
//        return "redirect:/login?error";
//    }
//
//    @GetMapping("/registration")
//    public String showRegistrationForm() {
//        return "registration";
//    }
//}
