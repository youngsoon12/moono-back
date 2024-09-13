package com.moono.JWT;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {
	
    // HS512 알고리즘에 맞는 강력한 비밀 키 생성
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 자동으로 안전한 키 생성
    private final long EXPIRATION_TIME = 1000 * 60 * 30; // 15분

    // JWT 토큰 생성 메서드
    public String generateToken(String userId) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SECRET_KEY)  // 자동 생성된 안전한 비밀키로 서명
                .compact();
    }
}