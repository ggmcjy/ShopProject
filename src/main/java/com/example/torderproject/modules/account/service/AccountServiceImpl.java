package com.example.torderproject.modules.account.service;

import com.example.torderproject.modules.account.dto.AccountDto;
import com.example.torderproject.modules.account.dto.RequestNewAccount;
import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;


    @Override
    public AccountDto createAccount(RequestNewAccount requestNewAccount) {
        Account account = new Account(requestNewAccount);
        Account savedAccount = accountRepository.save(account);
        return new AccountDto(savedAccount);
    }


    @Override
    public Account getAccountId(Long accountId) {
        return accountRepository.findById(accountId).orElseThrow(() -> new UsernameNotFoundException("Account Id Not Found"));
    }


}
