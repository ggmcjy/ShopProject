package com.example.torderproject.modules.order.controller.api;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.order.dto.OrderResponse;
import com.example.torderproject.modules.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/{cartId}")
    public ResponseEntity<?> cartOrder(@AuthenticationPrincipal Account account,
                                       @PathVariable(name="cartId") Long cartId) {
        orderService.order(account.getId(), cartId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<OrderResponse>> allOrder(@AuthenticationPrincipal Account account) {
        List<OrderResponse> orderResponses = orderService.showOrderAccount(account.getId());
        return ResponseEntity.status(HttpStatus.OK).body(orderResponses);
    }
}
