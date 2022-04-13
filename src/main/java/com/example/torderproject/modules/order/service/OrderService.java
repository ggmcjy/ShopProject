package com.example.torderproject.modules.order.service;

import com.example.torderproject.modules.order.jpa.Order;

import java.util.List;

public interface OrderService {
    void order(Long accountId, Long cartId);

    List<Order> paymentAccount(Long accountId);
}
