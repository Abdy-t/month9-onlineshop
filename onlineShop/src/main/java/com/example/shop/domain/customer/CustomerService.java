package com.example.shop.domain.customer;

import com.example.shop.domain.cart.Cart;
import com.example.shop.domain.cart.CartRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository repository;
    private final CartRepository cartRepository;

    private final PasswordEncoder encoder;

    public CustomerResponseDTO register(CustomerRegisterForm form) {
        if (repository.existsByEmail(form.getEmail())) {
            throw new CustomerAlreadyRegisteredException();
        }
        var cart = Cart.builder().build();
        cartRepository.save(cart);

        var user = Customer.builder()
                .email(form.getEmail())
                .fullname(form.getName())
                .password(encoder.encode(form.getPassword()))
                .cart(cart)
                .build();

        repository.save(user);

        return CustomerResponseDTO.from(user);
    }

    public CustomerResponseDTO getByEmail(String email) {
        Customer user = repository.findByEmail(email)
                .orElseThrow(CustomerNotFoundException::new);

        return CustomerResponseDTO.from(user);
    }
}
