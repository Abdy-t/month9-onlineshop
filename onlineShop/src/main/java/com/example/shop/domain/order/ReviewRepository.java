package com.example.shop.domain.order;

import com.example.shop.domain.cart.CartStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer>,
        JpaSpecificationExecutor<Review> {

    List<Review> getAllByProduct_Id(int id);
}
