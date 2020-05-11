package com.example.shop.domain.category;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data @Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128) private String name;

    @Column(length = 128) private String image;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category") @OrderBy("name ASC")
//    List<Product> products;
}
