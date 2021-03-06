package com.example.torderproject.modules.cart.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByAccountId(Long accountId);

}
