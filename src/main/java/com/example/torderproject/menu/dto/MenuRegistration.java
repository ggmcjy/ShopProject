package com.example.torderproject.menu.dto;


import com.example.torderproject.account.jpa.Account;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Data
@Builder
public class MenuRegistration {

    private String menuName; //상품 명
    private Integer price; // 상품 가격
    private Integer quantity; //상품 수량
    private Integer stock; // 상품 재고
    private String profileImage; //상품 이미지
    private Account account;


}
