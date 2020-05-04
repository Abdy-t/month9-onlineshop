package com.example.shop.domain.customer;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

import static java.util.stream.Collectors.toList;

@Controller
@RequestMapping
@AllArgsConstructor
public class CustomerController {

    @GetMapping("/register")
    public String pageRegisterCustomer(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new CustomerRegisterForm());
        }
        return "register";
    }

    @GetMapping("/test")
    @ResponseBody
    public String getTestPage(@Valid CustomerRegisterForm form) {
        return form.getEmail();
    }

    @GetMapping("/profile")
    public String pageCustomerProfile() {
        return "profile";
    }

    @PostMapping("/register")
    public String registerPage(@Valid CustomerRegisterForm form,
                               BindingResult validationResult,
                               RedirectAttributes attributes) {

        // добавим в модель-представление нашу форму с данными
        attributes.addFlashAttribute("form", form);

        if (validationResult.hasFieldErrors()) {
            attributes.addFlashAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/register";
        }

        return "redirect:/profile";
    }

    @ExceptionHandler(BindException.class)
    private ResponseEntity<Object> handleBindExceptionResponseEntity(BindException ex) {
        var apiFieldErrors = ex.getFieldErrors()
                .stream()
                .map(fe -> String.format("%s -> %s", fe.getField(), fe.getDefaultMessage()))
                .collect(toList());
        return ResponseEntity.unprocessableEntity()
                .body(apiFieldErrors);
    }

}
