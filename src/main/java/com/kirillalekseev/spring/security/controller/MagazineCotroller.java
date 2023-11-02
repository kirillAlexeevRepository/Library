package com.kirillalekseev.spring.security.controller;

import com.kirillalekseev.spring.security.entity.Magazine;
import com.kirillalekseev.spring.security.service.util.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MagazineCotroller {

    @Autowired
    private MagazineService magazineService;

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




    @GetMapping("/requestToReturnMagazine")
    public String requestToReturn(@RequestParam("magazineId") Integer magazineId){


        return "redirect:/users_item_info";
    }
}
