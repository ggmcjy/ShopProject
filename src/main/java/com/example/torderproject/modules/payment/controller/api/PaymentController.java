package com.example.torderproject.modules.payment.controller.api;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> orderPayment(@AuthenticationPrincipal Account account) {
        paymentService.payment(account.getId());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
