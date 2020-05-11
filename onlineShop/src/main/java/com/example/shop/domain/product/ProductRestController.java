package com.example.shop.domain.product;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RestController
@RequestMapping("/api/products/")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductRestController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(Pageable pageable) {
        return productService.getProducts(pageable).getContent();
    }

    @GetMapping("/search/{name}")
    public List<ProductDTO> searchByName(@PathVariable("name") String name, Pageable pageable) {
        return productService.searchByName(name, pageable).getContent();
    }
    @GetMapping("/search/{minPrice}/{maxPrice}")
    public List<ProductDTO> searchByPrice(@PathVariable float minPrice, @PathVariable float maxPrice, Pageable pageable) {
        var a = productService.searchByPrice(minPrice, maxPrice, pageable).getContent();
        System.out.println("PRODUCT PRICE NAME " + a.get(0).getName());
        return a;
    }

    @GetMapping("/{id:\\d+?}")
    public ProductDTO brandPage(@PathVariable int id) {
        return productService.getProduct(id);
    }
}
