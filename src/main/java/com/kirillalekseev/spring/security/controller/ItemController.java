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

import java.util.ArrayList;
import java.util.List;

@Controller
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("users_item_info")
    public String getUserItem(Model model){

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

        return "user-items";
    }

    @GetMapping("/take_return_requests")
    public String takeReturnRequests(Model model){
        List <Item> ItemsList = itemService.getReturnsRequestsItems();
        model.addAttribute("ItemsList",ItemsList);
        //Все айтемы со статусом  Reqested to Take  или  Reqested to Return
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
