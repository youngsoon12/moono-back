package com.moono.JWT;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    // HS512 알고리즘에 맞는 강력한 비밀 키 생성
    private final Key SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512); // 자동으로 안전한 키 생성
    private final long EXPIRATION_TIME = 1000 * 60 * 90; // 30분

    // JWT 토큰 생성 메서드
    public String generateToken(String userId, String nickName, boolean oneMission, boolean twoMission,
                                boolean threeMission, boolean fourMission, boolean fiveMission) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

        // 사용자 정보를 Claims에 추가
        Map<String, Object> claims = new HashMap<>();
        claims.put("nickName", nickName);
        claims.put("oneMission", oneMission);
        claims.put("twoMission", twoMission);
        claims.put("threeMission", threeMission);
        claims.put("fourMission", fourMission);
        claims.put("fiveMission", fiveMission);

        // JWT 생성
        return Jwts.builder()
                .setClaims(claims)          // 사용자 정보를 Claims로 추가
                .setSubject(userId)          // 토큰 주체 (userId)
                .setIssuedAt(now)            // 토큰 발급 시간
                .setExpiration(expiryDate)   // 토큰 만료 시간
                .signWith(SECRET_KEY)        // 비밀 키로 서명
                .compact();
    }
}