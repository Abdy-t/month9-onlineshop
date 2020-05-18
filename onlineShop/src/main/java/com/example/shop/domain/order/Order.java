package com.example.shop.domain.order;

import com.example.shop.domain.brand.Brand;
import com.example.shop.domain.category.Category;
import com.example.shop.domain.customer.Customer;
import com.example.shop.domain.product.Product;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE) @NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column
    private LocalDateTime date;

    @Column
    private int qty;

    @Column
    private float price;
}
