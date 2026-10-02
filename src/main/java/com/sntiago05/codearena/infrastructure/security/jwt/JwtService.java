package com.sntiago05.codearena.infrastructure.security.jwt;

import com.sntiago05.codearena.application.ports.out.TokenGeneratorPort;
import com.sntiago05.codearena.application.ports.out.TokenParserPort;
import com.sntiago05.codearena.application.ports.out.data.GeneratedRefreshToken;
import com.sntiago05.codearena.domain.user.UserRole;
import com.sntiago05.codearena.infrastructure.security.config.JwtProperties;
import com.sntiago05.codearena.infrastructure.security.config.TokenProperties;
import com.sntiago05.codearena.application.ports.out.claims.AccessTokenClaims;
import com.sntiago05.codearena.application.ports.out.claims.RefreshTokenClaims;
import com.sntiago05.codearena.application.ports.out.data.AccessTokenData;
import com.sntiago05.codearena.application.ports.out.data.RefreshTokenData;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

/**
 * Service for generating and parsing JWT tokens.
 */
@Service
@RequiredArgsConstructor
public class JwtService implements TokenParserPort, TokenGeneratorPort {

    private final JwtProperties jwtProperties;

    private SecretKey getSigningKey(TokenProperties tokenProperties) {
        return Keys.hmacShaKeyFor(tokenProperties.secret().getBytes(StandardCharsets.UTF_8));
    }

    private Date calculateExpiry(Date now, TokenProperties tokenProperties) {
        return new Date(now.getTime() + tokenProperties.expiration().toMillis());
    }

    private JwtBuilder buildBaseToken(UUID userId, Date now, Date expiry) {
        return Jwts.builder()
                .subject(userId.toString())
                .issuedAt(now)
                .expiration(expiry);
    }

    @Override
    public String generateAccessToken(AccessTokenData data) {
        TokenProperties tokenProperties = jwtProperties.access();
        Date now = new Date();
        return buildBaseToken(data.userId(), now, calculateExpiry(now, tokenProperties))
                .claim("role", data.role().name())
                .signWith(getSigningKey(tokenProperties))
                .compact();
    }

    @Override
    public GeneratedRefreshToken generateRefreshToken(RefreshTokenData data) {
        TokenProperties tokenProperties = jwtProperties.refresh();
        Date now = new Date();
        Date expiry = calculateExpiry(now, tokenProperties);

        String token = buildBaseToken(data.userId(), now, expiry)
                .signWith(getSigningKey(tokenProperties))
                .compact();
        LocalDateTime expiresAt = LocalDateTime.ofInstant(expiry.toInstant(), ZoneId.systemDefault());

        return new GeneratedRefreshToken(token, expiresAt);
    }

    private JwtParser buildJwtParser(TokenProperties tokenProperties) {
        return Jwts.parser().verifyWith(getSigningKey(tokenProperties)).build();
    }

    @Override
    public AccessTokenClaims parseAccessToken(String token) {
        Claims claims = buildJwtParser(jwtProperties.access()).parseSignedClaims(token).getPayload();
        return new AccessTokenClaims(UUID.fromString(claims.getSubject()), UserRole.valueOf(claims.get("role", String.class)));
    }

    @Override
    public RefreshTokenClaims parseRefreshToken(String token) {
        Claims claims = buildJwtParser(jwtProperties.refresh()).parseSignedClaims(token).getPayload();
        return new RefreshTokenClaims(UUID.fromString(claims.getSubject()));
    }

}
