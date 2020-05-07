package com.example.shop.domain.product;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SearchProduct {
    private String name;
    @Size(min=1, max=7, message = "length must be >= 1 and <= 7")
    @Pattern(regexp = "\\d+", message = "should contain only numbers")
    private float minPrice;
    @Size(min=1, max=7, message = "length must be >= 1 and <= 7")
    @Pattern(regexp = "\\d+", message = "should contain only numbers")
    private float maxPrice;
    private String brand;
    @Size(min=2, max=24, message = "length must be >= 2 and <= 24")
    @Pattern(regexp = "^[^\\d\\s]+$", message = "should contain only letters")
    private String category;
}
