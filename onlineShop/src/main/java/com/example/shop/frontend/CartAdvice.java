package com.example.shop.frontend;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CartAdvice {

    @ModelAttribute(Constants.CART_ID)
    public List<String> getCartModel(HttpSession session) {
        Object list = session.getAttribute(Constants.CART_ID);
        if (list == null) {
            session.setAttribute(Constants.CART_ID, new ArrayList<>());
        }
        return (List<String>) session.getAttribute(Constants.CART_ID);
    }

}
