package com.example.shop.domain.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

}
