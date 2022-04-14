package com.example.torderproject.modules.cart.service;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.service.AccountService;
import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.cart.dto.ResponseCart;
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

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Transactional
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{

    private final CartRepository cartRepository;
    private final MenuService menuService;
    private final CartItemRepository cartItemRepository;
    private final AccountService accountService;

    @Override
    public void addCart(Long accountId, Long menuId, RequestAddCart requestAddCart) {
        // 유저 id로 해당 유저의 장바구니 찾기
        Cart cart = cartRepository.findByAccountId(accountId);
        // 장바구니가 존재하지 않는다면
        if (cart == null) {
            Account account = accountService.getAccountId(accountId);
            cart = Cart.createCart(account);
            cartRepository.save(cart);
        }

        Menu menu = menuService.selectOneMenu(menuId);
        CartItem cartItem = cartItemRepository.findByCartIdAndMenuId(cart.getId(), menu.getId());

        if (cartItem == null) {
            cartItem = CartItem.createCartItem(cart, menu, requestAddCart.getCount());
            cartItemRepository.save(cartItem);
        } else {
            cartItem.update(requestAddCart.getCount()); // 장바구니 변경감지 (수량 변경)
        }
        cart.addCount(requestAddCart.getCount()); // 장바구니 총개수
    }

    @Override
    public void allCartItemDelete(Long accountId) {
        List<CartItem> cartItems = cartItemRepository.findAll();
        for (CartItem cartItem : cartItems) {
            if (Objects.equals(cartItem.getCart().getId(), accountId)) {
                cartItem.getCart().clearCount();
                cartItemRepository.deleteById(cartItem.getId());

            }
        }
    }

    @Override
    public ResponseCart CartMenuByAccountId(Account account) {
        Cart carts = cartRepository.findByAccountId(account.getId());
        return new ResponseCart(carts);
    }

}
