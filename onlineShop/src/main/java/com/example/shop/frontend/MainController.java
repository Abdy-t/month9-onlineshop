package com.example.shop.frontend;

import com.example.shop.domain.brand.BrandRepository;
import com.example.shop.domain.brand.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final BrandService brandService;


    @RequestMapping("/")
    public String getMainPage(Model model) {
        model.addAttribute("brands", brandService.findAll());
        return "index";
    }

    @RequestMapping("/jql/{name}")
    public String getMainPageJql(Model model, @PathVariable("name") String name) {
        model.addAttribute("brands", brandService.getByName(name));
        return "index";
    }

}
