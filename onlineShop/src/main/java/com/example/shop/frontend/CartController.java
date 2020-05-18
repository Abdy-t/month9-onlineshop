package com.example.shop.frontend;

import com.example.shop.domain.cart.CartService;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping("/cart")
    public String cart(Model model, @SessionAttribute(name = Constants.CART_ID, required = false) List<String> cart) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("cartItems", cartService.getCartsByEmail(userDetails.getUsername()));
            return "cart";
        } catch (Exception ex) {

        }
        if (cart != null) {
            List<ProductDTO> products = new ArrayList<>();
            for (String idProduct : cart) {
                var product = productService.getProduct(Integer.parseInt(idProduct));
                products.add(product);
            }
            for (ProductDTO productDTO : products) {

            }
            model.addAttribute("cartItems", products);
        }
        return "cart";
    }

    // это метод для асинхронных запросов
    // демонстрация добавления в "корзину" через параметр @SessionAttribute cart_model
    @PostMapping("/cart")
    @ResponseBody
    public boolean addToListRest(@RequestParam String value, @SessionAttribute(name = Constants.CART_ID, required = false) List<String> cart) {
        if (cart != null) {
            cart.add(value);
        }

        return true;
    }

    // метод для добавления в "корзину" через форму
    // демонстрация добавления через объект HttpSession session
    @PostMapping("/cart/add")
    public String addProductToCart(@RequestParam String value, @RequestParam int quantity, HttpSession session) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            cartService.putProduct(value, quantity, userDetails.getUsername());
        } catch (Exception ex) {

        }

        if (session != null) {
            var attr = session.getAttribute(Constants.CART_ID);
            if (attr == null) {
                session.setAttribute(Constants.CART_ID, new ArrayList<String>());
            }
            try {
                var list = (List<String>) session.getAttribute(Constants.CART_ID);
                list.add(value);
            } catch (ClassCastException ignored) {
            }
        }
        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeProductFromCart(@RequestParam String value, @RequestParam int quantity, HttpSession session) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            cartService.removeProductFromCart(value, userDetails.getUsername(), quantity);
        } catch (Exception ex) {

        }
        session.removeAttribute(Constants.CART_ID);
        return "redirect:/cart";
    }
    // в идеале это должно быть @DeleteMapping, но web-формы не поддерживают
    // запросы с методами отличными от get и post
    // по этому у нас адрес метода немного "странный" :)
    @PostMapping("/cart/empty")
    public String emptyCart(HttpSession session) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            cartService.removeCart(userDetails.getUsername());
        } catch (Exception ex) {

        }
        session.removeAttribute(Constants.CART_ID);
        return "redirect:/cart";
    }
}
