package com.sntiago05.codearena.infrastructure.security.jwt;

import com.sntiago05.codearena.infrastructure.security.config.JwtProperties;
import com.sntiago05.codearena.infrastructure.security.config.TokenProperties;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class JwtService {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey(TokenProperties tokenProperties) {
        return Keys.hmacShaKeyFor(tokenProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    private JwtBuilder buildBaseToken(UUID userId, TokenProperties tokenProperties) {
        Date now = new Date();
        Date expiry = new Date(now.getTime() + tokenProperties.expiration().toMillis());
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(now)
                .expiration(expiry);
    }

    public String generateAccessToken(AccessTokenData data) {
        TokenProperties tokenProperties = jwtProperties.access();
        return buildBaseToken(data.userId(),
                tokenProperties)
                .claim("role", data.role())
                .signWith(
                        getSigningKey(
                                tokenProperties))
                .compact();
    }

    public String generateRefreshToken(RefreshTokenData data) {
        TokenProperties tokenProperties = jwtProperties.refresh();
        return buildBaseToken(data.userId(), tokenProperties)
                .signWith(
                        getSigningKey(
                                tokenProperties
                        ))
                .compact();
    }

}
