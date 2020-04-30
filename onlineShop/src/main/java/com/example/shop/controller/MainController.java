package com.example.shop.controller;

import com.example.shop.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    BrandRepository brandRepository;

    @RequestMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("brands", brandRepository.findAll());
        return "index";
    }

    @RequestMapping("/jql/{name}")
    public String getMainPageJql(Model model, @PathVariable("name") String name) {
        model.addAttribute("brands", brandRepository.getByName(name));
        return "index";
    }

}
