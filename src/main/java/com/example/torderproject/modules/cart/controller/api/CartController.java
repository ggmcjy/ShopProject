package com.example.torderproject.modules.cart.controller.api;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.cart.dto.ResponseCart;
import com.example.torderproject.modules.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{menuId}")
    public ResponseEntity<?> addCartMenu(@PathVariable(name = "menuId") Long menuId,
                                         @AuthenticationPrincipal Account account,
                                         @RequestBody RequestAddCart requestAddCart) {

        cartService.addCart(account.getId(), menuId, requestAddCart);

        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }

    @GetMapping("/all")
    public ResponseEntity<ResponseCart> CartMenu(@AuthenticationPrincipal Account account) {
        return ResponseEntity.status(HttpStatus.OK).body(cartService.CartMenuByAccountId(account));
    }

}
