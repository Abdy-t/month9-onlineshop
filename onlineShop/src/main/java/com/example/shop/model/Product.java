package com.example.shop.model;

import lombok.*;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.LAZY)

    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    @Column
    private int qty;

}