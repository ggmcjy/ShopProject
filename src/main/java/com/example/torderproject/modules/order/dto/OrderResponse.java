package com.example.torderproject.modules.order.dto;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.order.jpa.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {

    private String accountName; // 구매자
    private String menuName; // 주문 상품 이름
    private Integer menuPrice; // 주문 상품 가격
    private Integer menuCount; // 주문 상품 수량

    public OrderResponse(Order o) {
        accountName = o.getAccount().getUsername();
        menuName = o.getMenuName();
        menuPrice = o.getMenuPrice();
        menuCount = o.getMenuCount();
    }
}
