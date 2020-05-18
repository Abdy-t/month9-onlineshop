package com.example.shop.domain.order;

import com.example.shop.domain.cart.CartStory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer>,
                                         JpaSpecificationExecutor<CartStory> {

    List<Order> getAllByCustomer_Id(int id);
}
