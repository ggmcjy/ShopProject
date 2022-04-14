package com.example.torderproject.modules.order.service;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.service.AccountService;
import com.example.torderproject.modules.cart.jpa.CartItem;
import com.example.torderproject.modules.cart.jpa.CartItemRepository;
import com.example.torderproject.modules.cart.service.CartService;
import com.example.torderproject.modules.order.dto.OrderResponse;
import com.example.torderproject.modules.order.jpa.Order;
import com.example.torderproject.modules.order.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final AccountService accountService;
    private final CartItemRepository cartItemRepository;
    private final CartService cartService;

    @Override
    public void order(Long accountId, Long cartId) {

        Account account = accountService.getAccountId(accountId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cartId);

        List<Order> orders = getOrderAccountId(accountId);
        System.out.println("orders.size() = " + orders.size());
        if (orders.size() == 0) {
            for (CartItem cartItem : cartItems) {
                Order order = new Order(account, cartItem);
                orderRepository.save(order);
            }
        } else {
            for (CartItem cartItem : cartItems) {
                for (Order order : orders) {
                    if (order.getMenuId() == cartItem.getMenu().getId()) {
                        order.menuCountUp(cartItem);
                    }
                }
            }
        }
        cartService.allCartItemDelete(accountId);
    }

    @Override
    public List<Order> getOrderAccountId(Long accountId) {
        return orderRepository.findAllByAccountId(accountId);
    }

    @Override
    public List<OrderResponse> showOrderAccount(Long id) {
        List<Order> orders = getOrderAccountId(id);
        return orders.stream().map(OrderResponse::new).collect(Collectors.toList());
    }

    @Override
    public void deleteAll(Long id) {
        List<Order> orderList = getOrderAccountId(id);
        for (Order order : orderList) {
            orderRepository.deleteById(order.getId());
        }
    }


}
