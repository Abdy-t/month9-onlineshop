package com.example.shop.domain.cart;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartStoryRepository extends JpaRepository<CartStory, Integer>,
                                             JpaSpecificationExecutor<CartStory> {
    List<CartStory> getAllByCart_Id(int id);

    boolean existsCartStoryByCart_IdAndProduct_Id(int cart_id, int product_id);

    CartStory getByCart_IdAndProduct_Id(int cart_id, int product_id);

}
