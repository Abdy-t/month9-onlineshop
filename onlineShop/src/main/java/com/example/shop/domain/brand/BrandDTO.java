package com.example.shop.domain.brand;

import lombok.*;

@Getter
@Setter
@ToString
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BrandDTO {
    private int id;
    private String name;
    private String icon;

    public static BrandDTO from(Brand brand) {
        return builder()
                .id(brand.getId())
                .name(brand.getName())
                .icon(brand.getIcon())
                .build();
    }
}
