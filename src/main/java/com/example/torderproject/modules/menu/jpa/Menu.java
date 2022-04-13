package com.example.torderproject.modules.menu.jpa;

import com.example.torderproject.modules.cart.jpa.Cart;
import com.example.torderproject.modules.cart.jpa.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; //상품 명
    private Integer price; // 상품 가격

    @OneToMany(mappedBy = "menu")
    private List<CartItem> cartItems = new ArrayList<>(); // 장바구니



//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "account_id")
//    private Account account;
}
