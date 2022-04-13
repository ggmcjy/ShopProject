package com.example.torderproject.modules.payment.service;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.service.AccountService;
import com.example.torderproject.modules.order.jpa.Order;
import com.example.torderproject.modules.order.service.OrderService;
import com.example.torderproject.modules.payment.jpa.Payment;
import com.example.torderproject.modules.payment.jpa.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{

    private final OrderService orderService;
    private final PaymentRepository paymentRepository;
    private final AccountService accountService;

    @Override
    public void payment(Long accountId) {

        Account account = accountService.getAccountId(accountId);
        List<Order> orders = orderService.paymentAccount(accountId);

        Integer totalPayment = 0;
        Integer totalCount = 0;
        for (Order order : orders) {
            totalPayment += order.getMenuTotalPrice();
            totalCount += order.getMenuCount();
        }
        System.out.println("totalPayment = " + totalPayment);
        System.out.println("totalCount = " + totalCount);
        paymentRepository.save(new Payment(account, totalPayment, totalCount));
    }
}
