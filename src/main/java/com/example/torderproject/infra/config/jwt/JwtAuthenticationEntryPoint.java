package com.example.torderproject.infra.config.jwt;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        // 인증에 실패한 사용자의 response 에 HttpServletResponse.SC_UNAUTHORIZED 를 담아주도록 구현
//        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UnAuthorized");
        response.sendRedirect("/login");
    }
}
