package com.example.shop.domain.order;

import com.example.shop.domain.cart.Cart;
import com.example.shop.domain.cart.CartRepository;
import com.example.shop.domain.cart.CartStory;
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
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartStoryRepository cartStoryRepository;

    public List<ProductDTO> getOrdersByEmail(String userEmail) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        var order = orderRepository.getAllByCustomer_Id(customerRepository.findByEmail(userEmail).get().getId());
        List<ProductDTO> products = new ArrayList<>();
        for (Order o : order) {
            Product a = productRepository.getById(o.getProduct().getId());
            a.setQty(o.getQty());
            a.setPrice(o.getPrice());
            products.add(ProductDTO.from(a));
        }
        return products;
    }

    public void buyProduct(String idProduct, int quantity, String userEmail) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        Product product = productRepository.findById(Integer.parseInt(idProduct)).get();
        if (cartStoryRepository.existsCartStoryByCart_IdAndProduct_Id(cart.getId(), product.getId())) {
            var order = Order.builder()
                    .customer(customerRepository.findByEmail(userEmail).get())
                    .product(product)
                    .date(LocalDateTime.now())
                    .qty(quantity)
                    .price(product.getPrice() * quantity)
                    .build();
            orderRepository.save(order);
            return;
        }
    }
}
