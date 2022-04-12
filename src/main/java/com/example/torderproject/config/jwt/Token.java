package com.example.torderproject.config.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Token {

    private String accessToken;
    private String accessTokenExpirationDate;
    private String refreshToken;
    private String refreshTokenExpirationDate;

}
