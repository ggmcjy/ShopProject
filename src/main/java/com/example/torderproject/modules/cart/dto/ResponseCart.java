package com.example.torderproject.modules.cart.dto;

import com.example.torderproject.modules.cart.jpa.Cart;
import com.example.torderproject.modules.menu.dto.MenuDto;
import com.example.torderproject.modules.menu.dto.ResponseCartMenuDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCart {

    private Long id;
    private List<ResponseCartMenuDto> menus;
    private Integer totalCount; // 토탈 카운트

    public ResponseCart(Cart c) {
        if (c == null) return;
        this.id = c.getId();
        this.menus = c.getCartItems().stream().map(ResponseCartMenuDto::new).collect(Collectors.toList());
        this.totalCount = c.getCount();

    }
}
