package com.kirillalekseev.spring.security.controller;

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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

     @GetMapping("/book_info")
    public String getInfoAboutBookInLibrary(Model model){
    List<Book> allBook ;
    allBook = bookService.getAllBook();
    model.addAttribute("allBook" ,allBook);
    return "view_book";
    }
    @GetMapping("/addNewBook")
    public String addNewBook(Model model){
        Book book = new Book();
        model.addAttribute("book" ,book);
        return "Add-new-book";
    }
    @GetMapping("/addMore")
    public String addMoreBook(@RequestParam("bookId")Integer bookid){
     bookService.updateAmount(bookid);
       return "redirect:/book_info";
    }
    @GetMapping("/delBook")
    public String delOneBook(@RequestParam("bookId")Integer bookId){
         bookService.delBook(bookId);
        return "redirect:/book_info";
    }
    @PostMapping("/saveBook")
    public String saveNewBook(@Valid @ModelAttribute("book")Book book , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "Add-new-book";
        }else {
            book.setBookStatus("available");
            bookService.putBook(book);
            return "redirect:/book_info";
        }
    }
    @GetMapping("/requestToTakeBook")
    public String requestToTake(@RequestParam("bookId") Integer BookId  ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

         List<Integer> bookIdList = bookService.getBookIdFromItems(username);
         if(bookIdList.contains(BookId)){
             //Должно выводится сообщение что данная книга уже тобой взята
             return "redirect:/book_info";
         }else {
             User user = userService.getOneUser(username);
             bookService.setBookItemRequest(BookId, user);
             return "redirect:/book_info";
         }
    }
    @GetMapping("/requestToReturnBook")
    public String requestToReturn(@RequestParam("bookId") Integer BookId , @RequestParam("bookStatus") String bookStatus){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        bookService.setBookItemReturn(BookId ,username ,bookStatus);

         return "redirect:/users_item_info";
    }
}
