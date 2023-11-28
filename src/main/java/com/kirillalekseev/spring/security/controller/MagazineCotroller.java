package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.entity.User;
import com.kirillalekseev.spring.security.exception_handling.ItemIncorrectStatus;
import com.kirillalekseev.spring.security.exception_handling.NotAvailableStatusException;
import com.kirillalekseev.spring.security.service.util.MagazineService;
import com.kirillalekseev.spring.security.service.util.UserService;
import com.kirillalekseev.spring.security.technicalClasses.abstractStatus;
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
public class MagazineCotroller {

    @Autowired
    private MagazineService magazineService;
    @Autowired
    private UserService userService;

    @GetMapping("/magazine_info")
    public String getInfoAboutMagazineInLibrary(Model model , HttpServletRequest request, HttpSession session) {
        List<Magazine> allMagazine;
        allMagazine = magazineService.getAllMagazine();
        model.addAttribute("allMagazine", allMagazine);
        session.setAttribute("previousPage", request.getRequestURL().toString());
        return "view_magazine";
    }
    @GetMapping("/addNewMagazine")
    public String addNewMagzine(Model model){
        Magazine magazine = new Magazine();
        model.addAttribute("magazine" ,magazine);
        return "Add-new-magazine";
    }
    @PostMapping("/saveMagazine")
    public String saveNewMagazine(@Valid @ModelAttribute("magazine") Magazine magazine, BindingResult bindingResult,
    @RequestParam("imageFile") MultipartFile file){
        if(bindingResult.hasErrors()){
            return "Add-new-magazine";
        }else {
            if(!(file.isEmpty())){
                try{
                    magazine.setPhotoData(file.getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }}
            magazine.setStatus(abstractStatus.AVAILABLE.getDisplayName());
            magazineService.putMagazine(magazine);
            return "redirect:/magazine_info";
        }
    }
    @GetMapping("/requestToTakeMagazine")
    public String requestToTakeMagazine(@RequestParam("MagazineId") Integer MagazineId ) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        List<Integer> magazinIdList = magazineService.getMagazineIdFromItems(username);
        if (magazinIdList.contains(MagazineId)) {
            throw new NotAvailableStatusException("this magazine already in your item list ");
        } else {
            User user = userService.getOneUser(username);
            magazineService.setMagazineItemRequest(MagazineId, user);
        }
        return "redirect:/magazine_info";
    }
    @GetMapping("/requestToReturnMagazine")
    public String requestToReturn(@RequestParam("magazineId") Integer magazineId
            ,@RequestParam("magazineStatus") String magazineStatus ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        magazineService.setMagazineItemReturn(magazineId , username ,magazineStatus);
        return "redirect:/users_item_info";}
    @GetMapping("/addMoreMagazine")
    public String addMoreMagazine(@RequestParam("magazineId")Integer magazineId,@RequestParam ("amount")Integer amount){
        if(amount<=69){magazineService.addMoreMagazine(magazineId);
        }else{throw new NotAvailableStatusException("more then 70 magazines Library can't accommodate");}
    return "redirect:/magazine_info";
    }
    @GetMapping("/delOneMagazine")
    public String delOneMagazine(@RequestParam("magazineId")Integer magazineId){
        magazineService.delMagazine(magazineId);
        return "redirect:/magazine_info";
    }
    @ExceptionHandler
    public ResponseEntity<ItemIncorrectStatus> handleException(
            NotAvailableStatusException exception){
        ItemIncorrectStatus incorrectStatus = new ItemIncorrectStatus();
        incorrectStatus.setInfo(exception.getMessage());
        return new ResponseEntity<>(incorrectStatus, HttpStatus.NOT_FOUND);
    }
}

