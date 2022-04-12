package com.example.torderproject.auth.jpa;

import com.example.torderproject.account.jpa.Account;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

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
