package com.example.shop.domain.customer;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ResetRepository extends JpaRepository<PasswordResetToken, Integer> {

    boolean existsByToken(String token);

    void deleteAll();

    Optional<PasswordResetToken> findByToken(String token);
}
