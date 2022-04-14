package com.example.torderproject.modules.cart.service;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.cart.dto.ResponseCart;

import java.util.List;

public interface CartService {
    void addCart(Long accountId, Long menuId, RequestAddCart count);
    void allCartItemDelete(Long accountId);


    ResponseCart CartMenuByAccountId(Account account);
}
