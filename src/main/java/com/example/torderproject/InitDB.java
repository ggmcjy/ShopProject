package com.example.torderproject;

import com.example.torderproject.modules.account.dto.RequestNewAccount;
import com.example.torderproject.modules.account.service.AccountService;
import com.example.torderproject.modules.menu.service.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@RequiredArgsConstructor
public class InitDB {

    private final InitService initService;

    @PostConstruct
    public void init() {
        initService.accountInit(); //회원 db insert
//        initService.menuInit(); // 메뉴 db insert
    }

    @Component
    @RequiredArgsConstructor
    static class InitService {

        private final AccountService accountService;
        private final MenuService menuService;
        private final PasswordEncoder passwordEncoder;

        private void accountInit() {
            RequestNewAccount requestNewAccount = new RequestNewAccount();
            requestNewAccount.setUsername("root@asd.com");
            requestNewAccount.setPassword(passwordEncoder.encode("1234"));
            accountService.createAccount(requestNewAccount);
        }
    }

}
