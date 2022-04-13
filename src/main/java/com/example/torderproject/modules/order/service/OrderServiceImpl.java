package com.example.torderproject.modules.order.service;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.service.AccountService;
import com.example.torderproject.modules.cart.jpa.Cart;
import com.example.torderproject.modules.cart.jpa.CartItem;
import com.example.torderproject.modules.cart.jpa.CartItemRepository;
import com.example.torderproject.modules.cart.jpa.CartRepository;
import com.example.torderproject.modules.cart.service.CartService;
import com.example.torderproject.modules.order.jpa.Order;
import com.example.torderproject.modules.order.jpa.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


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

        for (CartItem cartItem : cartItems) {
            orderRepository.save(new Order(account, cartItem));
        }

        cartService.allCartItemDelete(accountId);
    }

    @Override
    public List<Order> paymentAccount(Long accountId) {
        return orderRepository.findAllByAccountId(accountId);
    }
}
