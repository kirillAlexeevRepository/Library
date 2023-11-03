package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Authorities;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.service.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import static com.kirillalekseev.spring.security.technicalClasses.PasswordHasher.hashPassword;

@Controller
public class UserController {

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

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers(Model model){
        List<User> allUser ;
        allUser = userService.getAllUsers();
        model.addAttribute("allUser" ,allUser);
        return"view_for_managers";
    }
    @GetMapping("/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user" ,user);
        return "Add-new-user";
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

    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user")User user ){
        user.setPassword(hashPassword(user.getPassword()));
        user.setEnabled((byte)1);
        Authorities authorities = new Authorities();
        authorities.setAuthority("ROLE_USER");
        authorities.setAuthUser(user);
        user.setUserAuthorities(authorities);
        userService.putOneUser(user);
        return "redirect:/manager_info";
    }


}
