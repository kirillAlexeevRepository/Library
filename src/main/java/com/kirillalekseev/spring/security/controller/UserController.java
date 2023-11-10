package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Authorities;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.service.util.ItemService;
import com.kirillalekseev.spring.security.service.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

import static com.kirillalekseev.spring.security.technicalClasses.PasswordHasher.hashPassword;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String getInfoForAllEmps(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getOneUser(username);
        model.addAttribute("user", user);
        return "view_for_all_users";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers(Model model) {
        List<User> allUser;
        allUser = userService.getAllUsers();
        model.addAttribute("allUser", allUser);
        return "view_for_managers";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "Add-new-user";
    }

    @GetMapping("/change_password")
    public String change_user_password(@ModelAttribute("user") User user) {
        return "change_user_password";
    }

    @PostMapping("/putPassword")
    public String putPasswordinUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "change_user_password";
        } else {
            userService.putNewPassword(hashPassword(user.getPassword()), user.getUsername());
            return "redirect:/manager_info";
        }
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult
            , Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "Add-new-user";
        } else {
            user.setEnabled((byte)1);
            user.setPassword(hashPassword(user.getPassword()));
            Authorities authorities = new Authorities();
            authorities.setAuthority("ROLE_USER");
            authorities.setAuthUser(user);
            user.setUserAuthorities(authorities);
            userService.putOneUser(user);
        }
        if(authentication!= null){
            return "redirect:/manager_info";
        }else{ return "redirect:/login";}
    }

    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam("username")String username){
            List<Item> itemlist = itemService.getUserItems(username);
            if(itemlist.isEmpty()){
                userService.deleteOneUser(username);}
            else {
                //Соббщение о том что у этого юзер есть взятые или запрошенные айтемы и удалить его нельзя}1
            }
        return "redirect:/manager_info";
    }

}