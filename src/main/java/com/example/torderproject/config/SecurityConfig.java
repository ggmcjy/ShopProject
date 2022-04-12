package com.example.torderproject.config;

import com.example.torderproject.config.jwt.JwtAuthenticationEntryPoint;
import com.example.torderproject.config.jwt.JwtTokenFilter;
import com.example.torderproject.config.jwt.JwtTokenProvider;
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
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtTokenFilter jwtTokenFilter;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/node_modules/**", "/js/**", "/css/**")
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

//    JwtTokenFilter extends OncePerRequestFilter
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().headers().frameOptions().disable();
        http
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //token 인증 이므로 session STATELESS 치리
                .and()
                .authorizeRequests()
                .antMatchers("/**").permitAll();
//                .antMatchers("/login/**").permitAll()
//                .antMatchers("/h2-console/**").permitAll()
//                .anyRequest().authenticated();

//        http.exceptionHandling()
//                .authenticationEntryPoint(jwtAuthenticationEntryPoint);
//
//        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
