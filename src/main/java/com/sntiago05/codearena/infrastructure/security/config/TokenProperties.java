package com.sntiago05.codearena.infrastructure.security.config;

import java.time.Duration;


public record TokenProperties(String secret, Duration expiration) {

}
