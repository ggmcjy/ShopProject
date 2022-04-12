package com.example.torderproject.account.controller.api;

import com.example.torderproject.account.dto.RequestLogin;
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

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RequestLogin requestLogin) {
        log.info("request data {}, {}",requestLogin.getUsername(), requestLogin.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("sssss");
    }




}
