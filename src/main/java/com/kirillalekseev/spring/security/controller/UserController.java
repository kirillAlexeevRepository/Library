package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Authorities;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.exception_handling.ItemIncorrectStatus;
import com.kirillalekseev.spring.security.exception_handling.NotAvailableStatusException;
import com.kirillalekseev.spring.security.service.util.ItemService;
import com.kirillalekseev.spring.security.service.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static com.kirillalekseev.spring.security.technicalClasses.PasswordHasher.hashPassword;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String openLibrary (Model model,HttpServletRequest request, HttpSession session) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getOneUser(username);
        model.addAttribute("user", user);
        session.setAttribute("previousPage", request.getRequestURL().toString());
        return "view_for_all_users";
    }

    @GetMapping("/manager_info")
    public String getInfoOnlyForManagers(Model model, HttpServletRequest request, HttpSession session) {
        List<User> allUser;
        allUser = userService.getAllUsers();
        model.addAttribute("allUser", allUser);
        session.setAttribute("previousPage", request.getRequestURL().toString());
        return "view_for_managers";
    }

    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "Add-new-user";
    }

    @GetMapping("/change_password")
    public String change_user_password(@ModelAttribute("user") User user,HttpServletRequest request, HttpSession session) {
        session.setAttribute("previousPage", request.getRequestURL().toString());
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
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
    Authentication authentications) {
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
        if(authentications != null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username = authentication.getName();
            User authuser = userService.getOneUser(username);
            if(authuser.getUserAuthorities().getAuthority().equals("ROLE_ADMIN")) {
                return "redirect:/manager_info";
            }else{ return "redirect:/login";}
        }else{ return "redirect:/login";}
    }

    @GetMapping("/delete_user")
    public String deleteUser(@RequestParam("username")String username){
            List<Item> itemlist = itemService.getUserItems(username);
            if(itemlist.isEmpty()){
                userService.deleteOneUser(username);}
            else {
                throw new NotAvailableStatusException("Can't Delete this User, (s)he has taken items or requests");
            }
        return "redirect:/manager_info";
    }
    @GetMapping("/UsersWithRequests")
    public String reqestsByUsers(
            @RequestParam(value = "bookId",required = false)Integer bookid ,
            @RequestParam(value = "magazineId",required = false)Integer magazineid ,
            Model model){
        List<User> UserList;
        if(bookid != null  ){
            UserList = userService.getUsersWithItems(bookid);
        } else if (magazineid !=null) {
            UserList = userService.getUsersWithItemsForMagazines(magazineid);
        } else{
            UserList = userService.getUsersWithItems();}
        model.addAttribute("UserListWithItems",UserList);
        return "userListWithItems";
    }

    @ExceptionHandler
    public ResponseEntity<ItemIncorrectStatus> handleException(
            NotAvailableStatusException exception){
        ItemIncorrectStatus incorrectStatus = new ItemIncorrectStatus();
        incorrectStatus.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectStatus, HttpStatus.NOT_FOUND);
    }
}