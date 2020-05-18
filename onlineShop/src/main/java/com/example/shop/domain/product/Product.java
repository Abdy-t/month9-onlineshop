package com.example.shop.domain.product;

import com.example.shop.domain.brand.Brand;
import com.example.shop.domain.cart.CartStory;
import com.example.shop.domain.category.Category;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128)
    private String name;

    @Column(length = 128)
    private String image;

    @Column
    private float price;

    @ManyToOne
    private Brand brand;

    @ManyToOne
    private Category category;

    @Column
    private int qty;

//    @ManyToMany(mappedBy = "products")
//    private Set<Cart> carts;
    @OneToMany(mappedBy = "product")
    private Set<CartStory> cartStories;


}