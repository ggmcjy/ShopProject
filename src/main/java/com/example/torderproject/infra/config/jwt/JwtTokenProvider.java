package com.example.torderproject.infra.config.jwt;

import com.example.torderproject.modules.account.service.CustomUserDetailsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final CustomUserDetailsService customUserDetailsService;
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
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(this.getClaims(token, "sub"));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    /**
     * 토큰에서 Claims 추출
     */
    public String getClaims(String token, String key) {
        return this.extractAllClaims(token)
                .getBody()
                .get(key, String.class);
    }

    /**
     * 토큰 생성
     */
    public Token createToken(String username){
        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        Date now = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Date accessDate = new Date(now.getTime() + ACCESS_VALIDITY_IN_MILLISECONDS);
        Date refreshDate = new Date(now.getTime() + REFRESH_VALIDITY_IN_MILLISECONDS);
        return Token.builder()
                .accessToken(this.generateToken(claims, now, accessDate))
                .refreshToken(this.generateToken(claims, now, refreshDate))
                .accessTokenExpirationDate(sdf.format(accessDate))
                .refreshTokenExpirationDate(sdf.format(refreshDate))
                .build();
    }

    /**
     * 토큰을 쿠키에 저장
     */
    public void createCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(HEADER_NAME, token)
                .httpOnly(true)
                .sameSite("lax")
                .maxAge(ACCESS_VALIDITY_IN_MILLISECONDS)
                .path("/")
                .build();
        response.addHeader("Set-Cookie", cookie.toString());
    }
}
