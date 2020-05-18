package com.example.shop.domain.brand;

import lombok.*;

import javax.persistence.*;

@Data @Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128) private String name;

    @Column(length = 128) private String icon;

}
