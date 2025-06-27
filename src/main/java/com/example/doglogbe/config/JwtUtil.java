package com.example.doglogbe.config;

import com.example.doglogbe.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
//import lombok.Value;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.ZonedDateTime;
import java.util.Date;

@Component
public class JwtUtil {
    private final Key key;
    private final long tokenExp;


    public JwtUtil(
            @Value("${jwt.secret}") String secretKey,
            @Value("${jwt.exp}") long tokenExp
    ) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
        this.tokenExp = tokenExp;
    }

    public String createAccessToken(Member member) {
        return createToken(member, tokenExp);
    }

    private String createToken(Member member, long expireTime) {
        Claims claims = Jwts.claims();
        claims.put("memberId", member.getId());
        claims.put("username", member.getUserName());
        claims.put("role", member.getRole());

        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime tokenValidity = now.plusSeconds(expireTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date.from(now.toInstant()))
                .setExpiration(Date.from(tokenValidity.toInstant()))
                .signWith(key, SignatureAlgorithm.ES256)
                .compact();
    }
}
