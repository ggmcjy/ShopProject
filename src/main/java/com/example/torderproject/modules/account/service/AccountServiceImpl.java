package com.example.torderproject.modules.account.service;

import com.example.torderproject.modules.account.dto.AccountDto;
import com.example.torderproject.modules.account.dto.RequestLogin;
import com.example.torderproject.modules.account.dto.RequestNewAccount;
import com.example.torderproject.modules.account.dto.ResponseLoginApi;
import com.example.torderproject.modules.account.jpa.Account;
import com.example.torderproject.modules.account.jpa.AccountRepository;
import com.example.torderproject.infra.auth.jpa.AuthRepository;
import com.example.torderproject.infra.auth.jpa.AuthToken;
import com.example.torderproject.infra.config.jwt.JwtTokenProvider;
import com.example.torderproject.infra.config.jwt.Token;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;
    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @Override
    public AccountDto createAccount(RequestNewAccount requestNewAccount) {
        Account account = new Account(requestNewAccount);
        Account savedAccount = accountRepository.save(account);
        return new AccountDto(savedAccount);
    }


    @Override
    public ResponseLoginApi login(HttpServletRequest request, HttpServletResponse response, RequestLogin requestLogin) throws Exception {

        Account accountEntity = accountRepository
                        .findByUsername(requestLogin.getUsername())
                        .orElseThrow(() -> new IllegalArgumentException("username not found"));

        AuthToken currentAuth = authRepository.findByAccountId(accountEntity.getId());

        String accessToken = "";
        String accessTokenExpirationDate = "";

        String refreshToken = "";
        String refreshTokenExpirationDate = "";

        if (currentAuth != null) {
            throw new Exception("Using Token");
        }


        if (ObjectUtils.isNotEmpty(accountEntity)) {
            if (passwordEncoder.matches(requestLogin.getPassword(), accountEntity.getPassword())) {
                Token token = jwtTokenProvider.createToken(accountEntity.getUsername());
                if (StringUtils.isNotBlank(token.getAccessToken())) {

                    log.info("token {}1", token.getAccessToken());
                    // auth 등록
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(accountEntity.getUsername(), accountEntity.getPassword());
                    log.info("authentication {}", authentication);
                    Authentication authenticate = authenticationManager.authenticate(authentication);
                    log.info("authenticate {}", authenticate.getPrincipal());
                    SecurityContextHolder.getContext().setAuthentication(authenticate);

                    accessToken = token.getAccessToken();
                    accessTokenExpirationDate = token.getAccessTokenExpirationDate();
                    jwtTokenProvider.createCookie(response,token.getAccessToken());

                }

                if (StringUtils.isNotBlank(token.getRefreshToken())) {
                    AuthToken authEntity = AuthToken.builder()
                            .refreshToken(token.getRefreshToken())
                            .account(accountEntity)
                            .refreshTokenExpirationDate(token.getRefreshTokenExpirationDate())
                            .build();

                    authRepository.delete(authEntity);
                    AuthToken auth = authRepository.save(authEntity);
                    refreshToken = auth.getRefreshToken();
                    refreshTokenExpirationDate = auth.getRefreshTokenExpirationDate();
                }
            } else {
                throw new Exception("비밀번호가 일치하지 않습니다.");
            }
        } else {
            throw new UsernameNotFoundException("User Not Found");
        }
        return new ResponseLoginApi(HttpStatus.OK.value(),
                new Token(accessToken,accessTokenExpirationDate,refreshToken,refreshTokenExpirationDate));

    }


}
