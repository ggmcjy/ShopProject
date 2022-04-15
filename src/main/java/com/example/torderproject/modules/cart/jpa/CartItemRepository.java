package com.example.torderproject.modules.cart.jpa;

import com.example.torderproject.modules.menu.jpa.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    CartItem findByCartIdAndMenuId(Long cartId, Long menuId);

    List<CartItem> findAllByCartId(Long cartId);

    CartItem findByMenuId(Menu menu);
}
