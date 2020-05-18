package com.example.shop.domain.cart;

import com.example.shop.domain.customer.Customer;
import com.example.shop.domain.customer.CustomerRepository;
import com.example.shop.domain.product.Product;
import com.example.shop.domain.product.ProductDTO;
import com.example.shop.domain.product.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final CartStoryRepository cartStoryRepository;


    public void putProduct(String idProduct, int quantity, String userEmail) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        Product product = productRepository.findById(Integer.parseInt(idProduct)).get();
        if (cartStoryRepository.existsCartStoryByCart_IdAndProduct_Id(cart.getId(), product.getId())) {
            var cartSt = cartStoryRepository.getByCart_IdAndProduct_Id(cart.getId(), product.getId());
            cartSt.setQuantity(cartSt.getQuantity() + quantity);
            cartStoryRepository.save(cartSt);
            return;
        }
        var cartStory = CartStory.builder()
                .cart(cart)
                .product(product)
                .quantity(quantity)
                .build();
        cartStoryRepository.save(cartStory);
    }

    public List<ProductDTO> getCartsByEmail(String userEmail) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        var cartStory = cartStoryRepository.getAllByCart_Id(cart.getId());
        List<ProductDTO> products = new ArrayList<>();
        for (CartStory c : cartStory) {
            Product a = productRepository.getById(c.getProduct().getId());
            a.setQty(c.getQuantity());
            products.add(ProductDTO.from(a));
        }
        return products;
    }

    public void removeCart(String userEmail) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        var cartSt = cartStoryRepository.getAllByCart_Id(cart.getId());
        for (CartStory c : cartSt) {
            cartStoryRepository.delete(c);
        }
    }

    public void removeProductFromCart(String idProduct, String userEmail, int quantity) {
        Cart cart = cartRepository.findById(customerRepository.findByEmail(userEmail).get().getCart().getId()).get();
        Product product = productRepository.findById(Integer.parseInt(idProduct)).get();
        var cartSt = cartStoryRepository.getByCart_IdAndProduct_Id(cart.getId(), product.getId());
        if (cartSt.getQuantity() <= quantity) {
            cartStoryRepository.delete(cartSt);
        } else if (cartSt.getQuantity() > quantity) {
            cartSt.setQuantity(cartSt.getQuantity() - quantity);
            cartStoryRepository.save(cartSt);
        }
    }
}
