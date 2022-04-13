package com.example.torderproject.modules.cart.service;

import com.example.torderproject.modules.cart.dto.RequestAddCart;

public interface CartService {
    void addCart(Long accountId, Long menuId, RequestAddCart count);
}
