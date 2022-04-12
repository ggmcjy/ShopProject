package com.example.torderproject.account.service;

import com.example.torderproject.account.dto.AccountDto;
import com.example.torderproject.account.dto.RequestLogin;
import com.example.torderproject.account.dto.RequestNewAccount;
import com.example.torderproject.account.dto.ResponseLoginApi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AccountService {

    AccountDto createAccount(RequestNewAccount requestNewAccount);
    ResponseLoginApi login(HttpServletRequest request, HttpServletResponse response, RequestLogin requestLogin) throws Exception;
}
