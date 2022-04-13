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
    private Account user; // 구매자

    private Integer count; //장바구니 수량

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems = new ArrayList<>(); // 장바구니

    public void createCart() {
        this.count = 0;
    }

    public void addCount(Integer count) {
        this.count += count;
    }
}
