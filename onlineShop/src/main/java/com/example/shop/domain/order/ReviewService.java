package com.example.shop.domain.order;

import com.example.shop.domain.cart.Cart;
import com.example.shop.domain.cart.CartRepository;
import com.example.shop.domain.cart.CartStoryRepository;
import com.example.shop.domain.customer.CustomerRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartStoryRepository cartStoryRepository;

    public void addComment(String idProduct, String comment, String userEmail) {
        var newComment = Review.builder()
                .customer(customerRepository.findByEmail(userEmail).get())
                .product(productRepository.findById(Integer.parseInt(idProduct)).get())
                .date(LocalDateTime.now())
                .comment(comment)
                .build();
        reviewRepository.save(newComment);
    }

    public List<ReviewDTO> getReviews(int idProduct) {
        var list = reviewRepository.getAllByProduct_Id(idProduct);
        List<ReviewDTO> reviewDTOS = new ArrayList<>();
        for (Review r : list) {
//            Product a = productRepository.getById(o.getProduct().getId());
//            a.setQty(o.getQty());
//            a.setPrice(o.getPrice());
            reviewDTOS.add(ReviewDTO.from(r));
        }
        return reviewDTOS;
    }
}
