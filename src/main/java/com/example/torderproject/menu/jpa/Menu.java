package com.example.torderproject.menu.jpa;

import com.example.torderproject.account.jpa.Account;

import javax.persistence.*;

@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String menuName; //상품 명
    private Integer price; // 상품 가격

    private Integer quantity; //상품 수량
    private Integer stock; // 상품 재고

    @Lob
    @Basic(fetch = FetchType.EAGER)
    private String profileImage; //상품 이미지

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

}
