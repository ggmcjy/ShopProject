package com.example.torderproject.modules.payment.controller;

import com.example.torderproject.modules.payment.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/{accountId}")
    public ResponseEntity<?> orderPayment(@PathVariable(name = "accountId") Long accountId) {
        paymentService.payment(accountId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
