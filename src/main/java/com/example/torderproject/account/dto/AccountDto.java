package com.example.torderproject.account.dto;

import com.example.torderproject.account.jpa.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String password;

    public AccountDto(Account savedAccount) {
        this.id = savedAccount.getId();
        this.username = savedAccount.getUsername();
        this.password = savedAccount.getPassword();
    }
}
