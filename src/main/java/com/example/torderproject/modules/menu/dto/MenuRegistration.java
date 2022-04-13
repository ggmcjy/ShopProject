package com.example.torderproject.modules.menu.dto;


import com.example.torderproject.modules.account.jpa.Account;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MenuRegistration {

    private String menuName; //상품 명
    private Integer price; // 상품 가격
    private Account account;

}
