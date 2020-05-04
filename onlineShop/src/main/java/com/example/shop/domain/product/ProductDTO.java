package com.example.shop.domain.product;

import com.example.shop.domain.brand.BrandDTO;
import com.example.shop.domain.category.CategoryDTO;
import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductDTO {
    private int id;
    private String name;
    private String image;
    private float price;
    private BrandDTO brand;
    private CategoryDTO category;
    private int qty;

    static ProductDTO from(Product product) {
        return builder()
                .id(product.getId())
                .name(product.getName())
                .image(product.getImage())
                .price(product.getPrice())
                .brand(BrandDTO.from(product.getBrand()))
                .category(CategoryDTO.from(product.getCategory()))
                .build();
    }
}
