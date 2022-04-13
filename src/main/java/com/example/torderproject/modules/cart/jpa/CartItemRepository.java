package com.example.torderproject.modules.cart.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndMenuId(Long cartId, Long menuId);
}
