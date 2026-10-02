package com.sntiago05.codearena.infrastructure.security.config;

import java.time.Duration;

/**
 * Configuration properties for individual token types.
 */
public record TokenProperties(String secret, Duration expiration) {

}
