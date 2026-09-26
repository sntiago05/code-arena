package com.sntiago05.codearena.infrastructure.security.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "security.jwt")
public record JwtProperties(
        TokenProperties access,
        TokenProperties refresh
) {
}
