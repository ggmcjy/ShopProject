package com.example.torderproject.modules.account.service;

import com.example.torderproject.modules.account.dto.AccountDto;
import com.example.torderproject.modules.account.dto.RequestLogin;
import com.example.torderproject.modules.account.dto.RequestNewAccount;
import com.example.torderproject.modules.account.dto.ResponseLoginApi;
import com.example.torderproject.modules.account.jpa.Account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    AccountDto createAccount(RequestNewAccount requestNewAccount);
    ResponseLoginApi login(HttpServletRequest request, HttpServletResponse response, RequestLogin requestLogin) throws Exception;

    Account getAccountId(Long accountId);
}
