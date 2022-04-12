package com.example.torderproject.menu.jpa;

import com.example.torderproject.account.jpa.Account;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //상품 명
    private Integer price; // 상품 가격

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id")
//    private Account account;
}
