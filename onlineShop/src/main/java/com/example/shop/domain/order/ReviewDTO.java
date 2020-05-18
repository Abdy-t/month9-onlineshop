package com.example.shop.domain.order;

import com.example.shop.domain.brand.BrandDTO;
import com.example.shop.domain.category.CategoryDTO;
import com.example.shop.domain.customer.CustomerResponseDTO;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductDTO;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewDTO {
    private int id;
    private String userEmail;
    private ProductDTO productDTO;
    private LocalDateTime date;
    private String comment;

    public static ReviewDTO from(Review review) {
        return builder()
                .id(review.getId())
                .userEmail(review.getCustomer().getEmail())
                .productDTO(ProductDTO.from(review.getProduct()))
                .date(LocalDateTime.now())
                .comment(review.getComment())
                .build();
    }
}
