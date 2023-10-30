package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Authorities;
import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.service.util.BookService;
import com.kirillalekseev.spring.security.service.util.MagazineService;
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
public class MyController {
    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private MagazineService magazineService;

    @GetMapping("/")
    public String getInfoForAllEmps(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User user = userService.getOneUser(username);
        model.addAttribute("user" , user);
        return "view_for_all_users";
    }

//    @GetMapping("/book_info")
//    public String getInfoOnlyForHR(Model model){
//        List<User> allUser ;
//        allUser = userService.getAllUsers();
//        model.addAttribute("allUser" ,allUser);
//        return "view_book";
//
//    }
     @GetMapping("/book_info")
    public String getInfoAboutBookInLibrary(Model model){
    List<Book> allBook ;
    allBook = bookService.getAllBook();
    model.addAttribute("allBook" ,allBook);
    return "view_book";

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

    @GetMapping("/addNewUser")
    public String addNewUser(Model model){
        User user = new User();
        model.addAttribute("user" ,user);
        return "Add-new-user";
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

    @GetMapping("/addNewBook")
    public String addNewBook(Model model){
        Book book = new Book();
        model.addAttribute("book" ,book);
        return "Add-new-book";
    }

    @PostMapping("/saveBook")
    public String saveNewBook(@ModelAttribute("book")Book book ){
        book.setBookStatus("available");
        bookService.putBook(book);
        return "redirect:/book_info";
    }

    @GetMapping("/magazine_info")
    public String getInfoAboutMagazineInLibrary(Model model) {
        List<Magazine> allMagazine;
        allMagazine = magazineService.getAllMagazine();
        model.addAttribute("allMagazine", allMagazine);
        return "view_magazine";
    }

    @GetMapping("/addNewMagazine")
    public String addNewMagzine(Model model){
        Magazine magazine = new Magazine();
        model.addAttribute("magazine" ,magazine);
        return "Add-new-magazine";
    }

    @PostMapping("/saveMagazine")
    public String saveNewMagazine(@ModelAttribute("magazine") Magazine magazine ){
        magazine.setStatus("available");
        magazineService.putMagazine(magazine);
        return "redirect:/magazine_info";
    }
}
