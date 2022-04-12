package com.example.torderproject.config.jwt;

import io.jsonwebtoken.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider {

    //app.yml 파일
    //jwt secret-key
    @Value("${jwt.secret-key}")
    private String SECRET_KEY;
    //jwt header-name
    @Value("${jwt.header-name}")
    private String HEADER_NAME;
    //jwt access-token-expire-length access token 기간
    @Value("${jwt.access-token-expire-length}")
    private long ACCESS_VALIDITY_IN_MILLISECONDS;
    //jwt refresh-token-expire-length refresh token 기간
    @Value("${jwt.refresh-token-expire-length}")
    private long REFRESH_VALIDITY_IN_MILLISECONDS;


    /**
     * 토큰 발급
     */
    private String generateToken(Map<String, Object> claims, Date now, Date expirationDate) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * 토큰 검증
     */
    public boolean validateToken(String token) {
        try {
            this.extractAllClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }

    /**
     * 토큰 Claims 분석
     * @param token
     */
    private Jws<Claims> extractAllClaims(String token) throws ExpiredJwtException {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token);
    }


    /**
     * 헤더에 있는 토큰 분석
     */
    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(HEADER_NAME);
        if (StringUtils.isNotBlank(bearerToken)) {
            return bearerToken;
        }
        return null;
    }

    /**
     * 스프링 시큐리티 검증
     */
    public Authentication getAuthentication(String accessToken) {
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken( userDetails, null, userDetails.getAuthorities());
//        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username)
//        UserDetails userDetails = loginService.loadUserByUsername(this.getClaims(token, "sub"));
//        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
        return null;
    }
}
