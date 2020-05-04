package com.example.shop.domain.category;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryDTO {
    private int id;
    private String name;
    private String image;

    public static CategoryDTO from(Category category) {
        return builder()
                .id(category.getId())
                .name(category.getName())
                .image(category.getImage())
                .build();
    }
}
