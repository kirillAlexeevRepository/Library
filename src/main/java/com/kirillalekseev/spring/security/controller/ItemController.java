package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.service.util.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("users_item_info")
    public String getUserItem(Model model,HttpServletRequest request, HttpSession session){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Item> items;
        items = itemService.getUserItems(username);

        List<Book> bookList = new ArrayList<>();
        List<Magazine> magazineList = new ArrayList<>();

        for(Item item : items){
            if(item.getBook()!=null){
                Book book = item.getBook();
                book.setBookStatus(item.getItemStatus());
                bookList.add(book);
            }else{
                Magazine magazine = item.getMagazine();
                magazine.setStatus(item.getItemStatus());
                magazineList.add(magazine);
            }
        }
        model.addAttribute("bookList",bookList);
        model.addAttribute("magazineList" , magazineList);
        session.setAttribute("previousPage", request.getRequestURL().toString());

        return "user-items";
    }
    @GetMapping("/take_return_requests")
    public String takeReturnRequests(Model model, HttpServletRequest request, HttpSession session){
        List <Item> ItemsList = itemService.getReturnsRequestsItems();
        List<Item> bookList = new ArrayList<>();
        List<Item>magazList = new ArrayList<>();
        for(Item item:ItemsList){
            if(item.getBook()!= null){
                bookList.add(item);
            }else{
               magazList.add(item);
            }
        }
        model.addAttribute("bookList",bookList);
        model.addAttribute("magazList",magazList);
        //Все айтемы со статусом  Reqested to Take  или  Reqested to Return
        session.setAttribute("previousPage", request.getRequestURL().toString());
        return "take-return-request";
    }
    @GetMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("ItemId") Integer ItemId,
                                @RequestParam("ItemStatus") String ItemStatus){
        itemService.acceptRequest(ItemId ,ItemStatus);

        return "redirect:/take_return_requests";
    }
    @GetMapping("/declineRequest")
    public String declineRequest(@RequestParam("ItemId") Integer ItemId,
                                 @RequestParam("ItemStatus") String ItemStatus){
        itemService.declineRequest(ItemId,ItemStatus);

        return "redirect:/take_return_requests";
    }
}
