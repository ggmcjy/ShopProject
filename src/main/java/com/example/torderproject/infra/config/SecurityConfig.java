package com.example.torderproject.infra.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**", "/js/**", "/css/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .headers().frameOptions().disable();

        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/menu/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(ajaxAwareAuthenticationEntryPoint("/login"));

        http
                .formLogin()
                .loginPage("/login") //인증 필요시 로그인 페이지
                .defaultSuccessUrl("/", true);//로그인 성공시 리다이랙팅 경로

        http.logout()
                .logoutSuccessUrl("/");

    }

    private AuthenticationEntryPoint ajaxAwareAuthenticationEntryPoint(String url) {
        return new AjaxAwareAuthenticationEntryPoint(url);
    }

}
