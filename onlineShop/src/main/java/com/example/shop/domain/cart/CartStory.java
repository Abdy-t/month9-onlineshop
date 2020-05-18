package com.example.shop.domain.cart;

import com.example.shop.domain.product.Product;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Entity
@Table(name = "cart_story")
public class CartStory {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @ManyToOne
        @JoinColumn(name = "cart_id")
        private Cart cart;

        @ManyToOne
        @JoinColumn(name = "product_id")
        private Product product;

        @Column
        private int quantity;

}
