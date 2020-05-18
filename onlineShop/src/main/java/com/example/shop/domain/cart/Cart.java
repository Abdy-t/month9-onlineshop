package com.example.shop.domain.cart;

import com.example.shop.domain.customer.Customer;
import com.example.shop.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @OneToOne(mappedBy = "cart")
    private Customer customer;

    @OneToMany(mappedBy = "cart")
    private Set<CartStory> cartStories;
//    @ManyToMany
//    @JoinTable(
//            name = "cart_and_product",
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "product_id"))
//    private Set<Product> products;
//
//    @JoinTable(name = "cart_and_product", joinColumns = @JoinColumn(name = "qty"))
//    private int qty;
}
