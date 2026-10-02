package com.sntiago05.codearena.application.ports.out;

/**
 * Port for hashing and verifying passwords.
 */
public interface PasswordHasherPort {
    boolean matches(String password, String hashedPassword);
    String encode(String password);
}
