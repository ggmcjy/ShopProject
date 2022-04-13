package com.example.torderproject.infra.auth.jpa;

import com.example.torderproject.modules.account.jpa.Account;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
@Builder
public class AuthToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String refreshToken;
    private String refreshTokenExpirationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Account account;



}
