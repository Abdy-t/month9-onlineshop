package com.example.shop.domain.brand;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandRestController {
    private final BrandService brandService;

    @GetMapping
    public List<BrandDTO> getBrands(Pageable pageable) {
        return brandService.getBrands(pageable).getContent();
    }

    @GetMapping("/{id:\\d+?}")
    public BrandDTO brandPage(@PathVariable int id) {
        return brandService.getBrand(id);
    }

}
