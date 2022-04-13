package com.example.torderproject.modules.order.controller.api;

import com.example.torderproject.modules.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{accountId}/{cartId}")
    public ResponseEntity<?> cartOrder(@PathVariable(name="accountId") Long accountId,
                                       @PathVariable(name="cartId") Long cartId) {
        orderService.order(accountId, cartId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
