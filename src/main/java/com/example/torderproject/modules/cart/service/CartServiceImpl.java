package com.example.torderproject.modules.cart.service;

import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.cart.jpa.Cart;
import com.example.torderproject.modules.cart.jpa.CartItem;
import com.example.torderproject.modules.cart.jpa.CartItemRepository;
import com.example.torderproject.modules.cart.jpa.CartRepository;
import com.example.torderproject.modules.menu.jpa.Menu;
import com.example.torderproject.modules.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MenuService menuService;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addCart(Long accountId, Long menuId, RequestAddCart requestAddCart) {

        // 유저 id로 해당 유저의 장바구니 찾기
        Cart cart = cartRepository.findByAccountId(accountId);
        // 장바구니가 존재하지 않는다면
        if (cart == null) {
//            cart = Cart.createCart(user);
            cartRepository.save(cart);
        }


        Menu menu = menuService.selectOneMenu(menuId);
        CartItem cartItem = cartItemRepository.findByCartIdAndMenuId(savedCart.getId(), menu.getId());

        if (cartItem == null) {
            cartItem = CartItem.createCartItem(savedCart, menu, requestAddCart.getCount());
            cartItemRepository.save(cartItem);
        }


        cartItem.update(savedCart, menu, requestAddCart.getCount());
        cartItemRepository.save(cartItem);
        cart.addCount(requestAddCart.getCount()); // 장바구니 총개수
    }
}
