package com.example.shop.frontend;

import com.example.shop.domain.brand.BrandDTO;
import com.example.shop.domain.brand.BrandService;
import com.example.shop.domain.customer.CustomerRegisterForm;
import com.example.shop.domain.customer.CustomerService;
import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductService;
import com.example.shop.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;


@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final BrandService brandService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final PropertiesService propertiesService;

    private static <T> void constructPageable(Page<T> list, int pageSize, Model model, String uri) {
        if (list.hasNext()) {
            model.addAttribute("nextPageLink", constructPageUri(uri, list.nextPageable().getPageNumber(), list.nextPageable().getPageSize()));
        }

        if (list.hasPrevious()) {
            model.addAttribute("prevPageLink", constructPageUri(uri, list.previousPageable().getPageNumber(), list.previousPageable().getPageSize()));
        }

        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("items", list.getContent());
        model.addAttribute("defaultPageSize", pageSize);
    }

    private static String constructPageUri(String uri, int page, int size) {
        return String.format("%s?page=%s&size=%s", uri, page, size);
    }
    @GetMapping
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
//        Page<ProductDTO> items = productService.getProducts(pageable);
//        String uri = uriBuilder.getRequestURI();
//        constructPageable(items, propertiesService.getDefaultPageSize(), model, uri);
        return "index";
    }
    @GetMapping("/search")
    public String getSearchPage(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        return "search";
    }
    @GetMapping("/brands")
    public String brands(Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        Page<BrandDTO> items = brandService.getBrands(pageable);
        String uri = uriBuilder.getRequestURI();
        constructPageable(items, propertiesService.getDefaultPageSize(), model, uri);
        return "brand";
    }

    @GetMapping("/brand/{brand_id}")
    public String brandPage(@PathVariable int brand_id, Model model, Pageable pageable, HttpServletRequest uriBuilder) {
        BrandDTO brandDTO = brandService.getBrand(brand_id);
        Page<ProductDTO> products = productService.getProductsByBrand(brand_id, pageable);
        model.addAttribute("brand", brandDTO);
        String uri = uriBuilder.getRequestURI();
        constructPageable(products, propertiesService.getDefaultPageSize(), model, uri);
        return "brand-page";
    }
    @GetMapping("/product/{product_id}")
    public String brandPage(@PathVariable int product_id, Model model) {
        ProductDTO productDTO = productService.getProduct(product_id);
        model.addAttribute("product", productDTO);
        return "product-page";
    }

    @RequestMapping("/jql/{name}")
    public String getMainPageJql(Model model, @PathVariable("name") String name) {
        model.addAttribute("brands", brandService.getByName(name));
        return "index";
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    private String handleRNF(ResourceNotFoundException ex, Model model) {
        model.addAttribute("resource", ex.getResource());
        model.addAttribute("id", ex.getId());
        return "resource-not-found";
    }

    @GetMapping("/profile")
    public String pageCustomerProfile(Model model, Principal principal)
    {
        var user = customerService.getByEmail(principal.getName());
        model.addAttribute("dto", user);
        return "profile";
    }

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("dto")) {
            model.addAttribute("dto", new CustomerRegisterForm());
        }

        return "register";
    }

    @PostMapping("/register")
    public String registerPage(@Valid CustomerRegisterForm customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {
        attributes.addFlashAttribute("dto", customerRequestDto);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        customerService.register(customerRequestDto);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

}
