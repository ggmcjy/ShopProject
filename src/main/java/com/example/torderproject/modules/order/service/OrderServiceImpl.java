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
        for (CartItem cartItem : cartItems) {
            newOrder(account, cartItem);
        }

        /**
         * 버그 발견해서 해결은 했지만 더나은 서비스를 위해 다시 고민 해봐야됌
         */
//        List<Order> orders = getOrderAccountId(accountId);
//        long count = getOrderMenuIdCartItemMenuIdCount(cartItems, orders);
//        if (orders.size() == 0 || count == 0) {
//            for (CartItem cartItem : cartItems) {
//                newOrder(account, cartItem);
//            }
//        } else {
//            for (CartItem cartItem : cartItems) {
//                for (Order order : orders) {
//                    if (order.getMenuId() == cartItem.getMenu().getId()) {
//                        order.menuCountUp(cartItem);
//                    }
//                }
//            }
//        }

        cartService.allCartItemDelete(accountId);
    }


    private long getOrderMenuIdCartItemMenuIdCount(List<CartItem> cartItems, List<Order> orders) {
        return orders
                .stream()
                .filter(o -> cartItems.stream().anyMatch(ci -> Objects.equals(o.getMenuId(), ci.getMenu().getId())))
                .count();
    }


    private void newOrder(Account account, CartItem cartItem) {
        Order order = new Order(account, cartItem);
        orderRepository.save(order);
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
