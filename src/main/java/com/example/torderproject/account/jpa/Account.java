package com.example.torderproject.account.jpa;

import com.example.torderproject.account.dto.RequestNewAccount;
import com.example.torderproject.menu.jpa.Menu;
import lombok.Getter;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;

//    @Enumerated(EnumType.STRING)
//    private AccountRole role;

    @OneToMany(mappedBy = "account")
    private List<Menu> menus = new ArrayList<>();


    public Account(RequestNewAccount requestNewAccount) {
        this.username = requestNewAccount.getUsername();
        this.password = requestNewAccount.getPassword();
    }
}
