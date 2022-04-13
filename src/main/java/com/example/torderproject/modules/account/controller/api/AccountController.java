package com.example.torderproject.modules.account.controller.api;

import com.example.torderproject.modules.account.dto.RequestLogin;
import com.example.torderproject.modules.account.dto.ResponseLoginApi;
import com.example.torderproject.modules.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;


    @PostMapping("/login")
    public ResponseEntity<ResponseLoginApi> login(HttpServletRequest request,
                                                  HttpServletResponse response,
                                                  @RequestBody RequestLogin requestLogin) throws Exception {

        ResponseLoginApi responseLoginApi = accountService.login(request,response,requestLogin);
        return ResponseEntity.status(HttpStatus.OK).body(responseLoginApi);
    }




}
