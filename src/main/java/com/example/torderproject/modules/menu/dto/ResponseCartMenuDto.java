package com.example.torderproject.modules.menu.dto;

import com.example.torderproject.modules.cart.jpa.CartItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCartMenuDto {

    private Long id;
    private String name; //메뉴 명
    private Integer price; // 메뉴 가격
    private Integer count; // 메뉴 갯수

    public ResponseCartMenuDto(CartItem ci) {
        this.id = ci.getMenu().getId();
        this.name = ci.getMenu().getName();
        this.price = ci.getMenu().getPrice();
        this.count = ci.getCount();
    }
}
