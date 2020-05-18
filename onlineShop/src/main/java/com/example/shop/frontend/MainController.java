package com.example.shop.frontend;

import com.example.shop.domain.brand.BrandDTO;
import com.example.shop.domain.brand.BrandService;
import com.example.shop.domain.customer.*;
import com.example.shop.domain.order.ReviewDTO;
import com.example.shop.domain.order.ReviewService;
import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductService;
import com.example.shop.exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping
@AllArgsConstructor
public class MainController {

    private final BrandService brandService;
    private final ProductService productService;
    private final CustomerService customerService;
    private final PropertiesService propertiesService;
    private final ReviewService reviewService;
    private final CustomerRepository repository;
    private final ResetRepository resetRepo;

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
    public String index(Model model, Pageable pageable, HttpServletRequest uriBuilder, HttpSession session) {
//        var map = new HashMap<String, Object>();
//        map.put("Идентификатор сессии", session.getId());
//
//        session.getAttributeNames()
//                .asIterator()
//                .forEachRemaining(key -> map.put(key, session.getAttribute(key).toString()));
//        for (String s: map.keySet()
//        ) {
//            System.out.println(s);
//        }
//        model.addAttribute("sessionAttributes", map);

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
        var reviewDTO = reviewService.getReviews(product_id);
        productDTO.setReview(reviewDTO);
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
    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/register")
    public String registerPage(@Valid CustomerRegisterForm customerRequestDto,
                               BindingResult validationResult,
                               RedirectAttributes attributes,
                               @RequestParam(name = "g-recaptcha-response") String captchaResponse) {
        attributes.addFlashAttribute("dto", customerRequestDto);
        String url = "https://www.google.com/recaptcha/api/siteverify";
        String params = "?secret=6LfeMvcUAAAAADN-fheeuDZ8mISg78nVjdXNle61&response="+captchaResponse;
        ReCaptchaResponse reCaptchaResponse = restTemplate.exchange(url+params, HttpMethod.POST,null,ReCaptchaResponse.class).getBody();
        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }
        if (reCaptchaResponse.isSuccess()){
            customerService.register(customerRequestDto);
            return "redirect:/login";
        }
        return "redirect:/register";

    }

    @GetMapping("/login")
    public String loginPage(@RequestParam(required = false, defaultValue = "false") Boolean error, Model model) {
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/forgot-password")
    public String pageForgotPassword(Model model) {
        return "forgot";
    }

    @PostMapping("/forgot-password")
    public String submitForgotPasswordPage(@RequestParam("email") String email,
                                           RedirectAttributes attributes) {

        if (!repository.existsByEmail(email)) {
            attributes.addFlashAttribute("errorText", "Entered email does not exist!");
            return "redirect:/";
        }

        PasswordResetToken pToken = PasswordResetToken.builder()
                .customer(repository.findByEmail(email).get())
                .token(UUID.randomUUID().toString())
                .build();

        resetRepo.deleteAll();
        resetRepo.save(pToken);

        return "redirect:/forgot-success";
    }

    @GetMapping("/forgot-success")
    public String pageResetPassword(Model model) {
        return "forgot-success";
    }

    @PostMapping("/reset-password")
    public String submitResetPasswordPage(@RequestParam("token") String token,
                                          @RequestParam("newPassword") String newPassword,
                                          RedirectAttributes attributes) {

        if (!resetRepo.existsByToken(token)) {
            attributes.addFlashAttribute("errorText", "Entered email does not exist!");
            return "redirect:/reset-password";
        }

        PasswordResetToken pToken = resetRepo.findByToken(token).get();
        Customer customer = repository.findById(pToken.getCustomer().getId()).get();
        customer.setPassword(new BCryptPasswordEncoder().encode(newPassword));

        repository.save(customer);

        return "redirect:/login";
    }

}
