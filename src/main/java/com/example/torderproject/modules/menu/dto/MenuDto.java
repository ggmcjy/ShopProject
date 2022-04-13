package com.example.torderproject.modules.menu.dto;

import com.example.torderproject.modules.menu.jpa.Menu;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MenuDto {

    private Long id;
    private String name; //상품 명
    private Integer price; // 상품 가격

    public MenuDto(Menu m) {
        this.id = m.getId();
        this.name = m.getName();
        this.price = m.getPrice();
    }
}
