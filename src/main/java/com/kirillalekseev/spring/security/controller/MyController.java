package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.service.PasswordHasher;
import com.kirillalekseev.spring.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

import static com.kirillalekseev.spring.security.service.PasswordHasher.hashPassword;

@Controller
public class MyController {
    @Autowired
    private UserService userService;


    @GetMapping("/")
    public String getInfoForAllEmps(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getOneUser(username);
        model.addAttribute("user" , user);
        return "view_for_all_users";
    }

    @GetMapping("/hr_info")
    public String getInfoOnlyForHR(Model model){
        List<User> allUser ;
        allUser = userService.getAllUsers();
        model.addAttribute("allUser" ,allUser);
        return "view_for_hr";

    }
    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers(Model model){
        List<User> allUser ;
        allUser = userService.getAllUsers();
        model.addAttribute("allUser" ,allUser);
        return"view_for_managers";
    }
    @GetMapping("/change_password")
    public String change_user_password(@ModelAttribute("user")User user){
    return "change_user_password";
    }

    @PostMapping("/putPassword")
    public String putPasswordinUser(@ModelAttribute("user")User user ){
       userService.putNewPassword(hashPassword(user.getPassword()), user.getUsername());
        return"redirect:/manager_info";
    }
}
