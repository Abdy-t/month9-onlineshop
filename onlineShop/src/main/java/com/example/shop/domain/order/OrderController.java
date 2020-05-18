package com.example.shop.domain.order;

import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductService;
import com.example.shop.frontend.Constants;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@AllArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ProductService productService;

    @GetMapping("/order")
    public String cart(Model model, @SessionAttribute(name = Constants.ORDER_ID, required = false) List<String> order) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            model.addAttribute("orderItems", orderService.getOrdersByEmail(userDetails.getUsername()));
            return "order";
        } catch (Exception ex) {

        }
        if (order != null) {
            List<ProductDTO> products = new ArrayList<>();
            for (String idProduct : order) {
                var product = productService.getProduct(Integer.parseInt(idProduct));
                products.add(product);
            }
            for (ProductDTO productDTO : products) {

            }
            model.addAttribute("orderItems", products);
        }
        return "order";
    }

    @PostMapping("/order/buy")
    public String addProductToCart(@RequestParam String value, @RequestParam int quantity, HttpSession session) {
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            orderService.buyProduct(value, quantity, userDetails.getUsername());
        } catch (Exception ex) {

        }

        if (session != null) {
            var attr = session.getAttribute(Constants.ORDER_ID);
            if (attr == null) {
                session.setAttribute(Constants.ORDER_ID, new ArrayList<String>());
            }
            try {
                var list = (List<String>) session.getAttribute(Constants.ORDER_ID);
                list.add(value);
            } catch (ClassCastException ignored) {
            }
        }
        return "redirect:/order";
    }
}
