package com.example.torderproject.modules.payment.jpa;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.order.jpa.Order;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account; // 구매자
    private Integer totalCount; // 총 결제 수량
    private Integer totalPayment; //총 결제 금액




    public Payment(Account account,Integer totalPayment, Integer totalCount) {
        this.account = account;
        this.totalCount = totalCount;
        this.totalPayment = totalPayment;
    }
}
