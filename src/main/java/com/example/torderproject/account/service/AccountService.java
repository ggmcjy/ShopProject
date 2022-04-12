package com.example.torderproject.account.service;

import com.example.torderproject.account.dto.AccountDto;
import com.example.torderproject.account.dto.RequestNewAccount;

public interface AccountService {

    AccountDto createAccount(RequestNewAccount requestNewAccount);
}
