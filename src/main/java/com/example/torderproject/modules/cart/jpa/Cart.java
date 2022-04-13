package com.example.torderproject.modules.cart.jpa;

import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.menu.jpa.Menu;
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
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_id")
    private Account account; // 구매자

    private Integer count; //장바구니 수량

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(); // 장바구니


    public static Cart createCart(Account account) {
        Cart cart = new Cart();
        cart.addCart(account);
        return cart;
    }
    private void addCart(Account account) {
        this.account = account;
        this.count = 0;
    }

    public void addCount(Integer count) {
        this.count += count;
    }

    public void clearCount() {
        this.count = 0;
    }
}
