package com.example.torderproject.modules.cart.jpa;

import com.example.torderproject.modules.cart.dto.RequestAddCart;
import com.example.torderproject.modules.menu.jpa.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="menu_id")
    private Menu menu;

    private int count; // 상품 개수

    public static CartItem createCartItem(Cart cart, Menu menu, Integer count) {
        CartItem cartItem = new CartItem();
        cartItem.create(cart, menu, count);
        return cartItem;
    }
    private void create(Cart cart, Menu menu, Integer count) {
        this.cart = cart;
        this.menu = menu;
        this.count = count;
    }

    public void update(Integer count) {
        this.count+=count;
    }


}
