package com.example.shop.domain.brand;

import lombok.*;

import javax.persistence.*;
import java.util.List;
@Data @Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "brands")

//@Data
//@Entity
//@Table(name="brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128) private String name;

    @Column(length = 128) private String icon;

//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "brand") @OrderBy("name ASC")
//    List<Product> products;

}
