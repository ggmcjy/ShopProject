package com.example.torderproject.account.controller.api;

import com.example.torderproject.account.dto.RequestLogin;
import com.example.torderproject.account.dto.ResponseLoginApi;
import com.example.torderproject.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLogin requestLogin) {
        log.info("request data {}, {}",requestLogin.getUsername(), requestLogin.getPassword());
        ResponseLoginApi responseLoginApi = accountService.login(requestLogin);
        return ResponseEntity.status(HttpStatus.OK).body("sssss");
    }




}
