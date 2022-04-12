package com.example.torderproject.account.service;

import com.example.torderproject.account.dto.AccountDto;
import com.example.torderproject.account.dto.RequestNewAccount;
import com.example.torderproject.account.jpa.Account;
import com.example.torderproject.account.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
