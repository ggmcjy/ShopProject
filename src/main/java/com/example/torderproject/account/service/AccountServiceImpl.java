package com.example.torderproject.account.service;

import com.example.torderproject.account.dto.AccountDto;
import com.example.torderproject.account.dto.RequestLogin;
import com.example.torderproject.account.dto.RequestNewAccount;
import com.example.torderproject.account.dto.ResponseLoginApi;
import com.example.torderproject.account.jpa.Account;
import com.example.torderproject.account.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Override
    public AccountDto createAccount(RequestNewAccount requestNewAccount) {
        Account account = new Account(requestNewAccount);
        Account savedAccount = accountRepository.save(account);
        return new AccountDto(savedAccount);
    }

    @Override
    public ResponseLoginApi login(HttpServletRequest request, HttpServletResponse response, RequestLogin requestLogin) {

        Account accountEntity = accountRepository
                        .findByUsername(requestLogin.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("ID : '\" + username + \"' not found"));





        return null;
    }


}
