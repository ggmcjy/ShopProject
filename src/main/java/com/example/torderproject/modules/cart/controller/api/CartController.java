package com.example.torderproject.modules.cart.controller.api;

import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/{accountId}/{menuId}")
    public ResponseEntity<?> addCartMenu(@PathVariable(name = "accountId") Long accountId,
                                         @PathVariable(name = "menuId") Long menuId,
                                         @RequestBody RequestAddCart requestAddCart) {

        cartService.addCart(accountId, menuId, requestAddCart);
        return ResponseEntity
                .status(HttpStatus.CREATED).build();
    }


}
