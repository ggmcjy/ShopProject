package com.example.torderproject.modules.order.jpa;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.cart.jpa.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="orders")
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account; // 구매자

    private Long menuId; // 주문 번호
    private String menuName; // 주문 상품 이름
    private Integer menuPrice; // 주문 상품 가격
    private Integer menuCount; // 주문 상품 수량
    private Integer menuTotalPrice; // 가격*수량

    public Order(Account account, CartItem cartItem) {
        this.account = account;
        this.menuId = cartItem.getMenu().getId();
        this.menuName = cartItem.getMenu().getName();
        this.menuPrice = cartItem.getMenu().getPrice();
        this.menuCount = cartItem.getCount();
        this.menuTotalPrice = cartItem.getMenu().getPrice() * cartItem.getCount();
    }


}
