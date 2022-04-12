package com.example.torderproject.account.service;

import com.example.torderproject.account.jpa.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final AccountRepository accountRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return accountRepository.findByUsername(username);
//                .orElseThrow(() -> new UsernameNotFoundException("ID : '" + username + "' not found"));
        return null;
    }
}
