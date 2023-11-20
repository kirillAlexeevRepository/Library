package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.exception_handling.ItemIncorrectStatus;
import com.kirillalekseev.spring.security.exception_handling.NotAvailableStatusException;
import com.kirillalekseev.spring.security.service.util.BookService;
import com.kirillalekseev.spring.security.service.util.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private UserService userService;

     @GetMapping("/book_info")
    public String getInfoAboutBookInLibrary(Model model , HttpServletRequest request, HttpSession session){
    List<Book> allBook ;
    allBook = bookService.getAllBook();
    model.addAttribute("allBook" ,allBook);
         String currentUrl = request.getRequestURL().toString();
         model.addAttribute("currentUrl", currentUrl);
         session.setAttribute("previousPage", currentUrl);
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
    public String saveNewBook(@Valid @ModelAttribute("book")Book book , BindingResult bindingResult
    ,@RequestParam("imageFile") MultipartFile file){
        if (bindingResult.hasErrors()) {
            return "Add-new-book";
        } else {
            if (!file.isEmpty()) {
                try {
                    book.setPhotoData(file.getBytes());
                    // Другие операции с загруженным файлом
                } catch (IOException e) {
                    e.printStackTrace();
                    // Обработка ошибок при чтении файла
                }
            }
            book.setBookStatus("available");
            bookService.putBook(book);
            return "redirect:/book_info";
        }
     }
    @GetMapping("/requestToTakeBook")
    public String requestToTake( @RequestParam("bookId") Integer BookId ,Model model  ){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

         List<Integer> bookIdList = bookService.getBookIdFromItems(username);

         if(bookIdList.contains(BookId)){

             throw  new NotAvailableStatusException("This book already in yours item List ");
//             return "redirect:/book_info";
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

    @ExceptionHandler
    public ResponseEntity<ItemIncorrectStatus> handleException(
            NotAvailableStatusException exception){
        ItemIncorrectStatus incorrectStatus = new ItemIncorrectStatus();
        incorrectStatus.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectStatus, HttpStatus.NOT_FOUND);
    }
}
