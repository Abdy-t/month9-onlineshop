package com.example.shop.domain.product;

import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> name(String name) {
        return (root, query, cb) -> cb.like(root.get("name"),name);
    }

    public static Specification<Product> priceLessThan(float price) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> priceGreaterThan(float price) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get("price"), price);
    }

    public static Specification<Product> byPriceRange(float min, float max) {
        return priceLessThan(max).and(priceGreaterThan(min));
    }

    public static Specification<Product> result(String name, float min, float max) {
        return name(name).and(priceLessThan(max)).and(priceGreaterThan(min));
    }
}
