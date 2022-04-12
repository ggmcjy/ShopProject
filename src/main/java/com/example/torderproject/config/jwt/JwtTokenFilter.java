package com.example.torderproject.config.jwt;

import com.example.torderproject.auth.jpa.AuthRepository;
import com.example.torderproject.auth.jpa.AuthToken;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedHashSet;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthRepository authRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String accessToken = jwtTokenProvider.resolveToken(request);
        String refreshToken = null;

        //access 토큰 검증
        try {
            if (StringUtils.isNoneBlank(accessToken) && jwtTokenProvider.validateToken(accessToken)){
                Authentication auth = jwtTokenProvider.getAuthentication(accessToken);
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            //access 토큰 만료시 refresh 토큰 get
        } catch (ExpiredJwtException e) {
             AuthToken authToken = authRepository.findByAccountUsername(e.getClaims().getSubject());
            if (authToken != null) {
                refreshToken = authToken.getRefreshToken();
            }
            log.error("JWT expired error : {} ", e);

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            log.error("JWT filter internal error : {} ", e);
            return;
        }

        //refresh token 으로 access 토큰 재발급
        if (StringUtils.isNotBlank(refreshToken)) {
            try {
                try {
                    if (jwtTokenProvider.validateToken(refreshToken)) {
                        Authentication auth = jwtTokenProvider.getAuthentication(refreshToken);
                        SecurityContextHolder.getContext().setAuthentication(auth);
                        // new access 토큰 발급
                        String newAccessToken = jwtTokenProvider
                                .createToken(jwtTokenProvider.getClaims(refreshToken, "sub"))
                                .getAccessToken();

                        log.info("newAccessToken: {}" , newAccessToken);

                        jwtTokenProvider.createCookie(response, newAccessToken);
                    }
                } catch (ExpiredJwtException e) {
                    SecurityContextHolder.clearContext();
                    log.error("JWT expired error : {} ", e);
                }
            } catch (Exception e) {
                SecurityContextHolder.clearContext();
                log.error("JWT filter internal error : {} ", e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        Collection<String> excludePathPatterns = new LinkedHashSet<>();
        excludePathPatterns.add("/login/**");
        excludePathPatterns.add("/logout/**");
        excludePathPatterns.add("/");
        excludePathPatterns.add("/resources/**");

        return excludePathPatterns.stream()
                .anyMatch(pattern -> new AntPathMatcher().match(pattern, request.getServletPath()));
    }
}
