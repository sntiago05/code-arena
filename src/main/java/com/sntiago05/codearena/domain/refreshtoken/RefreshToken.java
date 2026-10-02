package com.sntiago05.codearena.domain.refreshtoken;

import com.sntiago05.codearena.domain.utils.ValidationUtils;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Authentication refresh token for extending user sessions.
 */
@Getter
public class RefreshToken {

    private UUID id;
    private UUID userId;
    private String token;
    private LocalDateTime expiresAt;
    private LocalDateTime createdAt;
    private LocalDateTime revokedAt;

    public RefreshToken(UUID id, UUID userId, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
        setId(id);
        setUserId(userId);
        setToken(token);
        setCreatedAt(createdAt);
        setExpiresAt(expiresAt);

        validateTimeInvariants(this.createdAt, this.expiresAt, null);
    }

    public RefreshToken(UUID userId, String token, LocalDateTime createdAt, LocalDateTime expiresAt) {
        setId(UUID.randomUUID());
        setUserId(userId);
        setToken(token);
        setCreatedAt(createdAt);
        setExpiresAt(expiresAt);

        validateTimeInvariants(this.createdAt, this.expiresAt, this.revokedAt);
    }

    public RefreshToken(UUID id, UUID userId, String token, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime revokedAt) {
        setId(id);
        setUserId(userId);
        setToken(token);
        setCreatedAt(createdAt);
        setExpiresAt(expiresAt);

        if (revokedAt != null) {
            setRevokedAt(revokedAt);
        }

        validateTimeInvariants(this.createdAt, this.expiresAt, this.revokedAt);
    }

    private void setId(UUID id) {
        ValidationUtils.requireNonNull(id, "Id");
        this.id = id;
    }

    private void setUserId(UUID userId) {
        ValidationUtils.requireNonNull(userId, "UserId");
        this.userId = userId;
    }

    private void setToken(String token) {
        ValidationUtils.requireNonEmpty(token, "Token");
        this.token = token;
    }

    private void setCreatedAt(LocalDateTime createdAt) {
        ValidationUtils.requireNonNull(createdAt, "CreatedAt");
        this.createdAt = createdAt;
    }

    private void setExpiresAt(LocalDateTime expiresAt) {
        ValidationUtils.requireNonNull(expiresAt, "ExpiresAt");
        this.expiresAt = expiresAt;
    }

    private void setRevokedAt(LocalDateTime revokedAt) {
        this.revokedAt = revokedAt;
    }

    /**
     * @param revokeTime Time of revocation
     * @throws IllegalStateException if token is already revoked
     */
    public void revoke(LocalDateTime revokeTime) {
        ValidationUtils.requireNonNull(revokeTime, "RevokeTime");
        if (this.revokedAt != null) {
            throw new IllegalStateException("Token is already revoked");
        }
        validateTimeInvariants(this.createdAt, this.expiresAt, revokeTime);
        this.revokedAt = revokeTime;
    }

    public void revoke() {
        revoke(LocalDateTime.now());
    }

    public boolean isExpired(LocalDateTime now) {
        return !now.isBefore(this.expiresAt);
    }

    public boolean isExpired() {
        return isExpired(LocalDateTime.now());
    }

    public boolean isRevoked() {
        return this.revokedAt != null;
    }

    public boolean isValid(LocalDateTime now) {
        return !isExpired(now) && !isRevoked();
    }

    public boolean isValid() {
        return isValid(LocalDateTime.now());
    }

    private void validateTimeInvariants(LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime revokedAt) {
        ValidationUtils.validateTimeOrder(createdAt, expiresAt, "Expiration time cannot be before creation time");
        if (revokedAt != null) {
            ValidationUtils.validateTimeOrder(createdAt, revokedAt, "Revocation time cannot be before creation time");
        }
    }
}
