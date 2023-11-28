package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Book;
import com.kirillalekseev.spring.security.entity.Item;
import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.service.util.ItemService;
import com.kirillalekseev.spring.security.technicalClasses.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Controller
public class ItemController {
    @Autowired
    ItemService itemService;
    @GetMapping("users_item_info")
    public String getUserItem(@RequestParam(value = "username" ,required = false) String username, Model model){
        List<Item> items;
        if(!StringUtils.hasText(username)){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        username = authentication.getName();
            items = itemService.getUserItems(username);
        }else {
            items = itemService.getUserItems(username);
            Iterator<Item> iterator = items.iterator();
            while(iterator.hasNext()){
                Item item =iterator.next();
                if(!ItemStatus.ALREADY_TAKEN.getDisplayName().equals(item.getItemStatus()) &&
                        !ItemStatus.REQUESTED_TO_RETURN.getDisplayName().equals(item.getItemStatus())){
                    iterator.remove();
                }
            }
        }
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
    public String takeReturnRequests(@RequestParam(value = "username" ,required = false) String username,Model model,
                                     HttpServletRequest request){
        String referer = request.getHeader("Referer");
        if(referer != null && referer.contains("take_return_requests")){
            username = (String) request.getSession().getAttribute("username");
        }
        List <Item> itemsList;
            itemsList = itemService.getReturnsRequestsItems(StringUtils.hasText(username) ? username : null);
            List<Item> bookList = new ArrayList<>();
            List<Item> magazList = new ArrayList<>();
            for (Item item : itemsList) {
                if (item.getBook() != null) {
                    bookList.add(item);
                } else {
                    magazList.add(item);
                }
            }
            model.addAttribute("bookList", bookList);
            model.addAttribute("magazList", magazList);
            model.addAttribute("username", username);
                if(!StringUtils.hasText(username)) {
                    return "take-return-request";
                }else{
                    return "take-return-request-forOneUser";
                }
    }
    @GetMapping("/acceptRequest")
    public String acceptRequest(@RequestParam("ItemId") Integer ItemId,
                                @RequestParam("ItemStatus") String ItemStatus,
                                                    HttpServletRequest request
                               ){
        String referer = request.getHeader("Referer");
        itemService.acceptRequest(ItemId ,ItemStatus);
        if(referer != null && referer.contains("take_return_requests")){
        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username);
        }
        return "redirect:/take_return_requests";
    }
    @GetMapping("/declineRequest")
    public String declineRequest(@RequestParam("ItemId") Integer ItemId,
                                 @RequestParam("ItemStatus") String ItemStatus,
                                 HttpServletRequest request){
        String referer = request.getHeader("Referer");
        itemService.declineRequest(ItemId,ItemStatus);
        if(referer != null && referer.contains("take_return_requests")){
        String username = request.getParameter("username");
        request.getSession().setAttribute("username", username);
        }
        return "redirect:/take_return_requests";
    }
}
