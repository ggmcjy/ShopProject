package com.example.torderproject.modules.order.service;

import com.example.torderproject.modules.order.dto.OrderResponse;
import com.example.torderproject.modules.order.jpa.Order;

import java.util.List;

public interface OrderService {
    void order(Long accountId, Long cartId);
    List<Order> getOrderAccountId(Long accountId);

    List<OrderResponse> showOrderAccount(Long id);

    void deleteAll(Long id);
}
