package com.example.shop.domain.product;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.tomcat.util.http.fileupload.FileUploadBase.MULTIPART_FORM_DATA;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private List<Product> list;

    @GetMapping("/search")
    public String getSearchPage(Model model) {
        model.addAttribute("products", list);
//        System.out.println("GET RESULT LIST ====" + list.get(0).getName());
        return "search";
    }

    @PostMapping(value = "/search", consumes=MULTIPART_FORM_DATA)
    public String getSearchResult(Model model, @RequestParam String name, @RequestParam float minPrice, @RequestParam float maxPrice, @RequestParam String brand, @RequestParam String category) {
        System.out.println(name);
        System.out.println(minPrice);
        System.out.println(maxPrice);
        System.out.println(brand);
        System.out.println(category);
        list.addAll(productService.getSearchResult(name, minPrice, maxPrice, brand, category));
        System.out.println("RESULT OF SEARCH -- -- -- " + list.get(0).getName());
//        return "redirect:/search/";
        model.addAttribute(productService.getSearchResult(name, minPrice, maxPrice, brand, category));
        return "search";

    }

}
